/**
 * 
 */
package io.proximax.proximity.security;

import org.apache.shiro.authz.permission.WildcardPermission;

import io.proximax.dfms.cid.Cid;
import io.proximax.dfms.cid.multibase.Multibase;

/**
 * @author tono
 *
 */
public class ProximityPermissions {
   public static final String DRIVE_DOMAIN = "drive";
   
   public static WildcardPermission drivePermission(Cid contract, String action) {
      String permissionString = String.format("%s:%s:%s", DRIVE_DOMAIN, action, encodeCid(contract));
      return new WildcardPermission(permissionString);
   }

   public static String encodeCid(Cid cid) {
      return cid.encode(Multibase.BASE_58_BTC);
   }
}
