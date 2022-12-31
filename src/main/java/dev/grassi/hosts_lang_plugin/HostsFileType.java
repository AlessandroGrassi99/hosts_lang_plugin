package dev.grassi.hosts_lang_plugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class HostsFileType extends LanguageFileType {

    public static final HostsFileType INSTANCE = new HostsFileType();

    private HostsFileType() {
        super(HostsLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Hosts File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Hosts language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "hosts";
    }

    @Override
    public Icon getIcon() {
        return null;
    }
}
