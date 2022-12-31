package dev.grassi.hosts_lang_plugin.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

public interface HostsTokenSets {
    TokenSet COMMENTS = TokenSet.create(HostsTypes.comment);
    TokenSet IPV4S = TokenSet.create(HostsTypes.IPv4);
    TokenSet IPV6S = TokenSet.create(HostsTypes.IPv6);
    TokenSet HOSTNAMES = TokenSet.create(HostsTypes.hostname);
    TokenSet HSPACES = TokenSet.create(HostsTypes.hspace);
    TokenSet END_LINES = TokenSet.create(HostsTypes.end_line);
    TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
}
