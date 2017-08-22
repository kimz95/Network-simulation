
package project;

public class Message {
        private String type;
        private String text;
        private Vertex source;
        private Vertex destination;
        private int retransmissions;

        
        public Message(String type, String text, Vertex source, Vertex destination, int retransmissions) {
            this.type = type;
            this.text = text;
            this.source = source;
            this.destination = destination;
            this.retransmissions = retransmissions;
        }

        public Vertex getSource() {
                    return source;
        }

        public Vertex getDestination() {
                    return destination;
        }

        public void print(){
        System.out.println(type+"\t"+text+"\t"+source+"\t"+destination+"\t"+retransmissions);
        }
}
