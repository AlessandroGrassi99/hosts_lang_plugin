package dev.grassi.hosts_lang_plugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import dev.grassi.hosts_lang_plugin.psi.HostsTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class HostsSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey ADDRESS =
            createTextAttributesKey("HOSTS_ADDRESS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey HOSTNAME =
            createTextAttributesKey("HOSTS_HOSTNAME", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("HOSTS_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("HOSTS_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] ADDRESS_KEYS = new TextAttributesKey[]{ADDRESS};
    private static final TextAttributesKey[] HOSTNAME_KEYS = new TextAttributesKey[]{HOSTNAME};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new HostsLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(HostsTypes.IPv4) || tokenType.equals(HostsTypes.IPv6)) {
            return ADDRESS_KEYS;
        }
        if (tokenType.equals(HostsTypes.hostname)) {
            return HOSTNAME_KEYS;
        }
        if (tokenType.equals(HostsTypes.comment)) {
            return COMMENT_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }

}