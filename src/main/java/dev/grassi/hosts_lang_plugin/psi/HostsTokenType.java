package dev.grassi.hosts_lang_plugin.psi;

import dev.grassi.hosts_lang_plugin.HostsLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class HostsTokenType extends IElementType {

    public HostsTokenType(@NotNull @NonNls String debugName) {
        super(debugName, HostsLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
