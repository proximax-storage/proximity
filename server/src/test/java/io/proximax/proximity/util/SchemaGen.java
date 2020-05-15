/**
 * 
 */
package io.proximax.proximity.util;

import java.io.File;
import java.util.EnumSet;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

/**
 * @author tono
 *
 */
public class SchemaGen {

   /**
    * 
    */
   public SchemaGen() {
      // TODO Auto-generated constructor stub
   }

   public static void main(String[] args) {
      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
      SessionFactory sessionFactory;
      Metadata metaData;
      try {
         metaData = new MetadataSources( registry ).buildMetadata();
         sessionFactory = metaData.buildSessionFactory();
      }
      catch (Exception e) {
         // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
         // so destroy it manually.
         StandardServiceRegistryBuilder.destroy( registry );
         throw new RuntimeException("failed to create session factory", e);
      }

      File output = new File("schemas/database.sql");
      if (!output.delete()) {
         throw new IllegalStateException("Failed to delete old DB file " + output.getAbsolutePath());
      }
      
      SchemaExport export = new SchemaExport();
      export.setHaltOnError(true);
      export.setFormat(true);
      export.setDelimiter(";");
      export.setOutputFile(output.getAbsolutePath());
      
      EnumSet<TargetType> targets = EnumSet.of(TargetType.SCRIPT);
      
      export.drop(targets, metaData);
      export.create(targets, metaData);
   }
}
