package com.example.recyclerviewpro;

/**
 * @Description
 * @Author PC
 * @QQ 1578684787
 */
class Model {
   private String name;
   private String groupName;

   public Model(String name, String groupName) {
      this.name = name;
      this.groupName = groupName;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getGroupName() {
      return groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }
}
