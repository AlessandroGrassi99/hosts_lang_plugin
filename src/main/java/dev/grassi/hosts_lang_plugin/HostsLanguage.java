package dev.grassi.hosts_lang_plugin;

import com.intellij.lang.Language;

public class HostsLanguage extends Language {

    public static final HostsLanguage INSTANCE = new HostsLanguage();

    private HostsLanguage() {
        super("Hosts");
    }

}