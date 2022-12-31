// This is a generated file. Not intended for manual editing.
package dev.grassi.hosts_lang_plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static dev.grassi.hosts_lang_plugin.psi.HostsTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import dev.grassi.hosts_lang_plugin.psi.*;

public class HostsHostImpl extends ASTWrapperPsiElement implements HostsHost {

  public HostsHostImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HostsVisitor visitor) {
    visitor.visitHost(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HostsVisitor) accept((HostsVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public HostsAddress getAddress() {
    return findNotNullChildByClass(HostsAddress.class);
  }

  @Override
  @NotNull
  public HostsHostEnd getHostEnd() {
    return findNotNullChildByClass(HostsHostEnd.class);
  }

  @Override
  @NotNull
  public HostsHostnames getHostnames() {
    return findNotNullChildByClass(HostsHostnames.class);
  }

}
