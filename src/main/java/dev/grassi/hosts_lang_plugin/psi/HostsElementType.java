package dev.grassi.hosts_lang_plugin.psi;

import dev.grassi.hosts_lang_plugin.HostsLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class HostsElementType extends IElementType {
    public HostsElementType(@NotNull @NonNls String debugName) {
        super(debugName, HostsLanguage.INSTANCE);
    }
}