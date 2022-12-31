package dev.grassi.hosts_lang_plugin;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import dev.grassi.hosts_lang_plugin.psi.HostsHost;
import dev.grassi.hosts_lang_plugin.psi.HostsHostname;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HostsAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the Psi Element is an expression
        if (!(element instanceof HostsHost)) {
            return;
        }

        List<HostsHostname> conflicts = HostsUtil.getHostnamesWithConflicts((HostsHost) element);
        for (HostsHostname conflict : conflicts) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Hostname conflict, already defined for another IP address")
                .range(conflict.getTextRange())
                .highlightType(ProblemHighlightType.ERROR)
                .create();
        }
        List<HostsHostname> duplicates = HostsUtil.getDuplicateHostnames((HostsHost) element);
        for (HostsHostname duplicate : duplicates) {
            holder.newAnnotation(HighlightSeverity.WARNING, "Duplicate hostname for the same IP address")
                .range(duplicate.getTextRange())
                .highlightType(ProblemHighlightType.WARNING)
                .create();
        }

    }

}