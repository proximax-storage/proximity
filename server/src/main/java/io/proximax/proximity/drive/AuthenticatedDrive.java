/**
 * 
 */
package io.proximax.proximity.drive;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import io.proximax.dfms.DriveRepository;
import io.proximax.dfms.cid.Cid;
import io.proximax.dfms.model.drive.DriveContent;
import io.proximax.dfms.model.drive.DriveItem;
import io.proximax.proximity.security.ProximityPermissions;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Provide authenticated drive API. This means calls are authorized and default contract can be used
 */
public class AuthenticatedDrive {
   private static final String KEY_DEFAULT_CONTRACT = "io.proximax.proximity.defaultContract";
   private final DriveRepository drive;
   
   /**
    * 
    */
   public AuthenticatedDrive(DriveRepository drive) {
      this.drive = drive;
   }
   
   protected <T> T authorizeCall(Cid actualContract, String permission, Supplier<T> supplier) {
      // verify permissions
      Subject currentUser = SecurityUtils.getSubject();
      if (!currentUser.isPermitted(ProximityPermissions.drivePermission(actualContract, permission))) {
         throw new IllegalStateException("Unauthorized access");
      } else {
         return supplier.get();
      }
   }
   
   /**
    * retrieve default contract from the session
    * 
    * @return the Cid of the default contract
    */
   protected static Cid getDefaultContract() {
      return (Cid)SecurityUtils.getSubject().getSession().getAttribute(KEY_DEFAULT_CONTRACT);
   }
   
   /**
    * convert checked to unchecked exception
    * 
    * @param contract the affected contract
    * @param path path on a drive
    * @param content content to add
    * @return cid of content
    */
   private Observable<Cid> addUnchecked(Cid contract, String path, DriveContent content) {
      try {
         return drive.add(contract, path, content);
      } catch (IOException e) {
         throw new IllegalStateException("Failed to add content", e);
      }
   }
   
   public Observable<Cid> add(Optional<Cid> contract, String path, DriveContent content) {
      Cid actualContract = contract.orElse(getDefaultContract());
      return authorizeCall(actualContract, "add", () -> addUnchecked(actualContract, path, content));
   }
   
   public Observable<DriveContent> get(Optional<Cid> contract, String path) {
      Cid actualContract = contract.orElse(getDefaultContract());
      return authorizeCall(actualContract, "get", () -> drive.get(actualContract, path));
   }
   
   public Completable remove(Optional<Cid> contract, String path) {
      Cid actualContract = contract.orElse(getDefaultContract());
      return authorizeCall(actualContract, "remove", () -> drive.remove(actualContract, path));
   }
   
   public Completable move(Optional<Cid> contract, String sourcePath, String destinationPath) {
      Cid actualContract = contract.orElse(getDefaultContract());
      return authorizeCall(actualContract, "move", () -> drive.move(actualContract, sourcePath, destinationPath));
   }
   
   public Completable copy(Optional<Cid> contract, String sourcePath, String destinationPath) {
      Cid actualContract = contract.orElse(getDefaultContract());
      return authorizeCall(actualContract, "copy", () -> drive.copy(actualContract, sourcePath, destinationPath));
   }
   
   public Completable makeDir(Optional<Cid> contract, String path) {
      Cid actualContract = contract.orElse(getDefaultContract());
      return authorizeCall(actualContract, "makeDir", () -> drive.makeDir(actualContract, path));
   }
   
   public Observable<DriveItem> stat(Optional<Cid> contract, String path) {
      Cid actualContract = contract.orElse(getDefaultContract());
      return authorizeCall(actualContract, "stat", () -> drive.stat(actualContract, path));
   }
   
   public Observable<List<DriveItem>> ls(Optional<Cid> contract, String path) {
      Cid actualContract = contract.orElse(getDefaultContract());
      return authorizeCall(actualContract, "ls", () -> drive.ls(actualContract, path));
   }
   
   public Completable flush(Optional<Cid> contract, String path) {
      Cid actualContract = contract.orElse(getDefaultContract());
      return authorizeCall(actualContract, "flush", () -> drive.flush(actualContract, path));
   }
}
