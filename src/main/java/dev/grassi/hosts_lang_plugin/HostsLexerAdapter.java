package dev.grassi.hosts_lang_plugin;

import com.intellij.lexer.FlexAdapter;

public class HostsLexerAdapter extends FlexAdapter {

    public HostsLexerAdapter() {
        super(new HostsLexer(null));
    }

}
