/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Kareem
 */
public class Vertex {
        final private String id;
        final private String name;
        final int x,y;
        int btypwr = 1000;
        String msg;


        public Vertex(String id, String name,int x,int y) {
                this.id = id;
                this.name = name;
                this.x=x;
                this.y=y;
        }
        public String getId() {
                return id;
        }

        public String getName() {
                return name;
        }
}
