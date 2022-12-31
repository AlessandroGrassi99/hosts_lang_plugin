// This is a generated file. Not intended for manual editing.
package dev.grassi.hosts_lang_plugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dev.grassi.hosts_lang_plugin.psi.impl.*;

public interface HostsTypes {

  IElementType address = new HostsElementType("address");
  IElementType host = new HostsElementType("host");
  IElementType host_end = new HostsElementType("host_end");
  IElementType hostname = new HostsElementType("hostname");
  IElementType hostnames = new HostsElementType("hostnames");

  IElementType IPv4 = new HostsTokenType("IPv4");
  IElementType IPv6 = new HostsTokenType("IPv6");
  IElementType comment = new HostsTokenType("comment");
  IElementType end_line = new HostsTokenType("end_line");
  IElementType hostname_value = new HostsTokenType("hostname_value");
  IElementType hspace = new HostsTokenType("hspace");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == address) {
        return new HostsAddressImpl(node);
      }
      else if (type == host) {
        return new HostsHostImpl(node);
      }
      else if (type == host_end) {
        return new HostsHostEndImpl(node);
      }
      else if (type == hostname) {
        return new HostsHostnameImpl(node);
      }
      else if (type == hostnames) {
        return new HostsHostnamesImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
