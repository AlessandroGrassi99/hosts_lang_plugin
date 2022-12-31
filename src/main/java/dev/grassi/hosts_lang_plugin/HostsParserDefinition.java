package dev.grassi.hosts_lang_plugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import dev.grassi.hosts_lang_plugin.parser.HostsParser;
import dev.grassi.hosts_lang_plugin.psi.HostsFile;
import dev.grassi.hosts_lang_plugin.psi.HostsTokenSets;
import dev.grassi.hosts_lang_plugin.psi.HostsTypes;
import org.jetbrains.annotations.NotNull;

public class HostsParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(HostsLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new HostsLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new HostsParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return HostsTokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new HostsFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return HostsTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode node1, ASTNode node2) {
//        if (node1.getElementType() == HostsTypes.ADDRESS) return SpaceRequirements.MUST;
//        else if (node1.getElementType() == HostsTypes.HOSTNAME && node2.getElementType() == HostsTypes.HOSTNAME) return SpaceRequirements.MUST;
        return SpaceRequirements.MAY;
    }
}