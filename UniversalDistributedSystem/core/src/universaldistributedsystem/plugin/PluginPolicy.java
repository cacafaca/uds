/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package universaldistributedsystem.plugin;

import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;

/**
 *
 * @author Nemanja
 */
public class PluginPolicy extends Policy {

    @Override
    public PermissionCollection getPermissions(CodeSource codeSource) {
        Permissions p = new Permissions();
        if (!codeSource.getLocation().toString().endsWith("/rogue.jar")) {
            p.add(new AllPermission());
        }
        return p;
    }

    /**
     * Does nothing.
     */
    public void refresh() {
    }
}
