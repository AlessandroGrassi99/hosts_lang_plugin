package dev.grassi.hosts_lang_plugin.psi;

import dev.grassi.hosts_lang_plugin.HostsFileType;
import dev.grassi.hosts_lang_plugin.HostsLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class HostsFile extends PsiFileBase {

    public HostsFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, HostsLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return HostsFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Hosts File";
    }
}
