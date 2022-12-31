// This is a generated file. Not intended for manual editing.
package dev.grassi.hosts_lang_plugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static dev.grassi.hosts_lang_plugin.psi.HostsTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class HostsParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return hostsFile(b, l + 1);
  }

  /* ********************************************************** */
  // IPv4 | IPv6
  public static boolean address(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address")) return false;
    if (!nextTokenIs(b, "<address>", IPv4, IPv6)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, address, "<address>");
    r = consumeToken(b, IPv4);
    if (!r) r = consumeToken(b, IPv6);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // address hostnames host_end
  public static boolean host(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "host")) return false;
    if (!nextTokenIs(b, "<host>", IPv4, IPv6)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, host, "<host>");
    r = address(b, l + 1);
    r = r && hostnames(b, l + 1);
    r = r && host_end(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // end_line | <<eof>>
  public static boolean host_end(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "host_end")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, host_end, "<host end>");
    r = consumeToken(b, end_line);
    if (!r) r = eof(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // hostname_value
  public static boolean hostname(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hostname")) return false;
    if (!nextTokenIs(b, hostname_value)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, hostname_value);
    exit_section_(b, m, hostname, r);
    return r;
  }

  /* ********************************************************** */
  // (hspace hostname)+ hspace?
  public static boolean hostnames(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hostnames")) return false;
    if (!nextTokenIs(b, hspace)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = hostnames_0(b, l + 1);
    r = r && hostnames_1(b, l + 1);
    exit_section_(b, m, hostnames, r);
    return r;
  }

  // (hspace hostname)+
  private static boolean hostnames_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hostnames_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = hostnames_0_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!hostnames_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "hostnames_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // hspace hostname
  private static boolean hostnames_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hostnames_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, hspace);
    r = r && hostname(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // hspace?
  private static boolean hostnames_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hostnames_1")) return false;
    consumeToken(b, hspace);
    return true;
  }

  /* ********************************************************** */
  // line*
  static boolean hostsFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hostsFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!line(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "hostsFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // host | end_line
  static boolean line(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line")) return false;
    boolean r;
    r = host(b, l + 1);
    if (!r) r = consumeToken(b, end_line);
    return r;
  }

}
