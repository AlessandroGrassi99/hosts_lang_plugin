package dev.grassi.hosts_lang_plugin;

import dev.grassi.hosts_lang_plugin.psi.HostsTypes;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

%%

%class HostsLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%ignorecase

Space = [\ \t]+
Comment = #.*
EndLine = \R


IPv4 = {Octal} "." {Octal} "." {Octal} "." {Octal}
Octal = (25[0-5]|2[0-4][0-9]|1[0-9][0-9]|0[0-9][0-9]|[0-9][0-9]|[0-9])

// https://man7.org/linux/man-pages/man3/inet_pton.3.html
IPv6Seg  = [0-9a-fA-F]{1,4}
Ipv6Case01 = ({IPv6Seg}:){7,7}{IPv6Seg}           // 1:2:3:4:5:6:7:8
Ipv6Case02 = ({IPv6Seg}:){1,7}:                   // 1::                                 1:2:3:4:5:6:7::
Ipv6Case03 = ({IPv6Seg}:){1,6}:{IPv6Seg}          // 1::8               1:2:3:4:5:6::8   1:2:3:4:5:6::8
Ipv6Case04 = ({IPv6Seg}:){1,5}(:{IPv6Seg}){1,2}   // 1::7:8             1:2:3:4:5::7:8   1:2:3:4:5::8
Ipv6Case05 = ({IPv6Seg}:){1,4}(:{IPv6Seg}){1,3}   // 1::6:7:8           1:2:3:4::6:7:8   1:2:3:4::8
Ipv6Case06 = ({IPv6Seg}:){1,3}(:{IPv6Seg}){1,4}   // 1::5:6:7:8         1:2:3::5:6:7:8   1:2:3::8
Ipv6Case07 = ({IPv6Seg}:){1,2}(:{IPv6Seg}){1,5}   // 1::4:5:6:7:8       1:2::4:5:6:7:8   1:2::8
Ipv6Case08 = {IPv6Seg}:((:{IPv6Seg}){1,6})        // 1::3:4:5:6:7:8     1::3:4:5:6:7:8   1::8
Ipv6Case09 = :((:{IPv6Seg}){1,7}|:)               // ::2:3:4:5:6:7:8    ::2:3:4:5:6:7:8  ::8       ::
Ipv6Case10 = ::(ffff(:0{1,4}){0,1}:){0,1}         // ::255.255.255.255  ::ffff:255.255.255.255  ::ffff:0:255.255.255.255 (IPv4-mapped IPv6 addresses and IPv4-translated addresses)
Ipv6Case11 = ({IPv6Seg}:){1,4}:{IPv4}             // 2001:db8:3:4::192.0.2.33  64:ff9b::192.0.2.33 (IPv4-Embedded IPv6 Address)

IPv6 = {Ipv6Case01} | {Ipv6Case02} | {Ipv6Case03} | {Ipv6Case04} | {Ipv6Case05} | {Ipv6Case06} | {Ipv6Case07}
     | {Ipv6Case08} | {Ipv6Case09} | {Ipv6Case10} | {Ipv6Case11}


// https://man7.org/linux/man-pages/man7/hostname.7.html
Hostname = (([a-z][a-z0-9-]*[a-z0-9] | [a-z]) ("." {HostnameElem})* ) | [a-z][a-z0-9-]*[a-z0-9] | [a-z][a-z0-9]*
HostnameElem = [a-z0-9][a-z0-9-]*[a-z0-9] | [a-z0-9]+


%state HOSTNAME

%%

<YYINITIAL> {
    {Space}     { return TokenType.WHITE_SPACE; }
    {Comment}   { return HostsTypes.comment; }
    {IPv4}      { yybegin(HOSTNAME); return HostsTypes.IPv4; }
    {IPv6}      { yybegin(HOSTNAME); return HostsTypes.IPv6; }
    {EndLine}   { return HostsTypes.end_line; }
}

<HOSTNAME> {
	{Space}    { yybegin(HOSTNAME); return HostsTypes.hspace; }
    {Hostname}     { yybegin(HOSTNAME); return HostsTypes.hostname_value; }
    {Comment}      { yybegin(HOSTNAME); return HostsTypes.comment; }
    {EndLine}      { yybegin(YYINITIAL); return HostsTypes.end_line; }
}

[^] { return TokenType.BAD_CHARACTER; }
