package dev.grassi.hosts_lang_plugin;

import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import dev.grassi.hosts_lang_plugin.psi.*;

import java.util.*;

public class HostsUtil {
    public static List<HostsHostname> getDuplicateHostnames(HostsHost host) {
        List<HostsHostname> duplicates = new ArrayList<>();

        HashMap<String, HostsHostname> visited = new HashMap<>();
        for(HostsHostname hostname : host.getHostnames().getHostnameList()) {
            if (checkHostnameDuplicate((HostsFile) host.getParent(), host.getAddress(), hostname)) {
                duplicates.add(hostname);
            } else if (visited.containsKey(hostname.getText())) {
                duplicates.add(hostname);
                HostsHostname visitedValue = visited.get(hostname.getText());
                if (visitedValue != null) {
                    duplicates.add(visitedValue);
                    visited.replace(hostname.getText(), null);
                }
            }

            if (!visited.containsKey(hostname.getText()))
                visited.put(hostname.getText(), hostname);
        }

        return duplicates;
    }

    public static List<HostsHostname> getHostnamesWithConflicts(HostsHost host) {
        List<HostsHostname> conflicts = new ArrayList<>();

        for(HostsHostname hostname : host.getHostnames().getHostnameList()) {
            if (checkOtherAddressHostnameConflicts((HostsFile) host.getParent(), host.getAddress(), hostname))
                conflicts.add(hostname);
        }

        return conflicts;
    }


    /**
     * Check if the hostname is already assigned to another address with the same type
     *
     * @param hostsFile current file
     * @param address to check
     * @param hostname to check
     * @return matching properties
     */
    public static boolean checkOtherAddressHostnameConflicts(HostsFile hostsFile, HostsAddress address, HostsHostname hostname) {
        HostsHost[] hosts = PsiTreeUtil.getChildrenOfType(hostsFile, HostsHost.class);
        if (hosts != null) {
            String address_type  = ((LeafPsiElement) address.getFirstChild()).getElementType().toString();
            for (HostsHost host : hosts) {
                // Skip different address type (IPv4 vs IPv6)
                String host_type = ((LeafPsiElement) host.getFirstChild().getFirstChild()).getElementType().toString();
                if (!address_type.equals(host_type)) continue;
                // Skip same addresses
                if (address.getText().equals(host.getAddress().getText())) continue;

                for (HostsHostname other_hostname : host.getHostnames().getHostnameList()) {
                    if (other_hostname.getText().equals(hostname.getText()))
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean checkHostnameDuplicate(HostsFile hostsFile, HostsAddress address, HostsHostname hostname) {
        HostsHost[] hosts = PsiTreeUtil.getChildrenOfType(hostsFile, HostsHost.class);
        if (hosts != null) {
            for (HostsHost host : hosts) {
                // Skip different address type (IPv4 vs IPv6)
                if (!(address.getFirstChild().getClass().equals(host.getAddress().getFirstChild().getClass()))) continue;
                // Skip different address
                if (!address.getText().equals(host.getAddress().getText())) continue;
                // Skip same addresses line
                if (address.getParent().equals(host)) continue;

                for (HostsHostname other_hostname : host.getHostnames().getHostnameList()) {
                    if (other_hostname.getText().equals(hostname.getText()))
                        return true;
                }
            }
        }
        return false;
    }
}

