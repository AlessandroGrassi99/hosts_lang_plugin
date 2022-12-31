// This is a generated file. Not intended for manual editing.
package dev.grassi.hosts_lang_plugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class HostsVisitor extends PsiElementVisitor {

  public void visitAddress(@NotNull HostsAddress o) {
    visitPsiElement(o);
  }

  public void visitHost(@NotNull HostsHost o) {
    visitPsiElement(o);
  }

  public void visitHostEnd(@NotNull HostsHostEnd o) {
    visitPsiElement(o);
  }

  public void visitHostname(@NotNull HostsHostname o) {
    visitPsiElement(o);
  }

  public void visitHostnames(@NotNull HostsHostnames o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
