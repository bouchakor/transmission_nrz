
import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class Signal extends Applet implements ActionListener {

            String  poly;
            Button nrzBtn,manchBtn,millerBtn,mancdifferBtn;
            char var;
            TextField nameField;



    public void blanco(){ //effacer le signal précedent
          Graphics g =getGraphics();
          g.setColor(Color.WHITE);

          g.clearRect(0, 85,2000, 85);
          g.clearRect(0, 158, 2000, 158);
          g.clearRect(0, 230, 2000, 230);

    }

    public void init()
   {
           //bouton NRZ
          nameField = new TextField("", 35);
          nrzBtn =new Button("NRZ");
          nrzBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
          nrzBtn.setForeground(Color.RED);
          nrzBtn.addActionListener(this);
          //bouton manchester
          manchBtn =new Button("Manchester");
          manchBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
          manchBtn.addActionListener(this);
          manchBtn.setForeground(Color.RED);

         //bouton manchester differentiel
          mancdifferBtn =new Button("Manchester_dif");
          mancdifferBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
          mancdifferBtn.setForeground(Color.RED);
          mancdifferBtn.addActionListener(this);

         //bouton miller
          millerBtn =new Button("Miller");
          millerBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
          millerBtn.setForeground(Color.RED);
          millerBtn.addActionListener(this);
          add(nameField);
          add(nrzBtn);
          add(manchBtn);
          add(mancdifferBtn);
          add( millerBtn);


    }


    public void actionPerformed(ActionEvent e) {
	        String tape = e.getActionCommand();
		poly =nameField.getText();
                blanco();
	       if ("NRZ".equals(tape))
		 	{
                        	NRZ();
                         }
		       else if ("Manchester".equals(tape))
		 	{
			       Manchester();
                         }
		       else if ("Manchester_dif".equals(tape))
			 {
			      ManchDifferentiel();
                          }
		       else if ("Miller".equals(tape))
		 	   {
		              Miller();
                            }

                 }

    public void NRZ(){//tracer le signal NRZ

          Graphics g =getGraphics();

           char currentChar[] = new char[1];

            poly = nameField.getText(); // poly prend les valeurs de TextField

            g.setColor(Color.gray); // tracer la grille avec une couleur grise
            g.drawLine(0, 85, poly.length()*30, 85);
            g.drawLine(0, 158, poly.length()*30, 158);
            g.drawLine(0, 230, poly.length()*30, 230);
            g.drawString("nV",poly.length(),85);
	    g.drawString("-nV",poly.length(),230);
	    g.drawString("0V",poly.length(),158);

            for (int i = 0; i < poly.length(); i++) {

                g.setColor(Color.red);

                if (poly.charAt(i) == '0') { // si c un zero alor trace en bas
                    g.drawLine(i*30, 230, (i*30)+30, 230);

                } else if (poly.charAt(i) == '1') { // si c 1 alors tracer en haut
                    g.drawLine(i*30, 85, (i*30)+30, 85);
                }

                g.setColor(Color.gray);

                 if(i > 0) {
                    if(poly.charAt(i) != poly.charAt(i-1))
                        g.setColor(Color.red);
                        g.drawLine(i*30, 85, i*30, 230);
                }



                currentChar[0] = poly.charAt(i); //tracer les bits correspendant en dessous de la grille
                g.setColor(Color.black);
                g.drawChars(currentChar, 0, 1, (i*30)+10, 245);
             }
    }

    public void Manchester(){//tracer le signal manchester

            Graphics g =getGraphics();

            char currentChar[] = new char[1];

            poly = nameField.getText();
             var = poly.charAt(0);
            g.setColor(Color.gray);
            g.drawLine(0, 85, poly.length()*30*2, 85);
            g.drawLine(0, 158, poly.length()*30*2, 158);
            g.drawLine(0, 230, poly.length()*30*2, 230);
            g.drawString("nV",poly.length(),85);
	    g.drawString("-nV",poly.length(),230);
	    g.drawString("0V",poly.length(),158);

            for (int i = 0; i < poly.length(); i++) {

                g.setColor(Color.red);

                if (poly.charAt(i) == '0') { // si c un zero alor trace de bas en haut
                    g.drawLine(i*2*30, 230, (i*2*30)+30, 230);
                    g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                    g.drawLine((i*2*30)+30, 85, (i*2*30)+60, 85);
                    if(i>0 && poly.charAt(i-1) == '0'){
                    g.drawLine((i*2*30), 85, (i*2*30), 230);

                    }
                } else if (poly.charAt(i) == '1') { // si c 1 alors tracer de haut en bas
                    g.drawLine((i*2*30), 85, (i*2*30)+30, 85);
                    g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                    g.drawLine((i*2*30)+60, 230, (i*2*30)+30, 230);
                    if(i>0 && poly.charAt(i-1) == '1'){
                    g.drawLine((i*2*30), 85, (i*2*30), 230);

                    }


                }

                currentChar[0] = poly.charAt(i);
                g.setColor(Color.black);
                g.drawChars(currentChar, 0, 1, (i*30*2)+20, 245);
             }
    }

    public void ManchDifferentiel(){

             Graphics g =getGraphics();

             char currentChar[] = new char[1];

            poly = nameField.getText();

            g.setColor(Color.gray);
            g.drawLine(0, 85, poly.length()*30*2, 85);
            g.drawLine(0, 158, poly.length()*30*2, 158);
            g.drawLine(0, 230, poly.length()*30*2, 230);
            g.drawString("nV",poly.length(),85);
	    g.drawString("-nV",poly.length(),230);
	    g.drawString("0V",poly.length(),158);


            var = poly.charAt(0); //var prend le 1er bits
            boolean tag=true; //true on monte , false on descend
            int etat_prec=0;
            g.setColor(Color.red);
            if(poly.charAt(0) == '1'){

                       g.drawLine(0*2*30, 230, (0*2*30)+30, 230);
                       g.drawLine((0*2*30)+30, 85, (0*2*30)+30, 230);
                       g.drawLine((0*2*30)+30, 85, (0*2*30)+60, 85);

                       tag=true; // bit 1 montant

                        }
              else {// si c un zero alor trace en bas

                    g.drawLine((0*2*30), 85, (0*2*30)+30, 85);
                    g.drawLine((0*2*30)+30, 85, (0*2*30)+30, 230);
                    g.drawLine((0*2*30)+60, 230, (0*2*30)+30, 230);

                    tag = false; //bit 0 descendant

             }
                currentChar[0] = poly.charAt(0);
                g.setColor(Color.black);
                g.drawChars(currentChar, 0, 1, (0*30*2)+20, 245);

            for (int i=1; i < poly.length(); i++) {

                etat_prec=(int) var;
                var = poly.charAt(i);
                g.setColor(Color.red);

                if(etat_prec == var){ //si le bit actuel est égal au bit precedent
                    if (poly.charAt(i) == '1') {
                    if(tag == true){ //si le front precedent est montant alors ce front est descendant
                    g.drawLine((i*2*30), 85, (i*2*30)+30, 85);
                    g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                    g.drawLine((i*2*30)+60, 230, (i*2*30)+30, 230);

                    tag=false;
                    }
                else  { //ce front est montant
                    g.drawLine(i*2*30, 230, (i*2*30)+30, 230);
                    g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                    g.drawLine((i*2*30)+30, 85, (i*2*30)+60, 85);
                    tag=true;

                }
                    }

                        else { //le bit est égala  zero
                               if(tag==true){//si le front precedent est montant alors ce front est montant
                                      g.drawLine(i*2*30, 230, (i*2*30)+30, 230);
                                      g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                                      g.drawLine((i*2*30)+30, 85, (i*2*30)+60, 85);
                                      tag=true;
                               }
                        else{  //si le front precedent est decendant ce font est descendant
                           g.drawLine((i*2*30), 85, (i*2*30)+30, 85);
                           g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                           g.drawLine((i*2*30)+60, 230, (i*2*30)+30, 230);

                           tag=false;

                        }
                        }
                        }
                        else{ // si les deux bits sont differents
                            if (poly.charAt(i) == '0') {
                                if(tag==true){ //si le front precedent est montant alors ce front est montant
                                    g.drawLine(i*2*30, 230, (i*2*30)+30, 230);
                                    g.drawLine(i*2*30, 230, i*2*30, 85);
                                    g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                                    g.drawLine((i*2*30)+30, 85, (i*2*30)+60, 85);

                                    tag=true;

                                }
                            else if (tag==false){//si le front precedent est descendant alors ce front est descendant
                                     g.drawLine((i*2*30), 85, (i*2*30)+30, 85);
                                     g.drawLine(i*2*30, 85, i*2*30, 230);
                                     g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                                     g.drawLine((i*2*30)+60, 230, (i*2*30)+30, 230);
                                     tag=false;
                            }

                            }

                 else {//l'etat present est 1 et l'etat precedent est 0 donc renversement
                    if(tag==true){//si le front precedent est montant alors ce front est descendant
                        g.drawLine((i*2*30), 85, (i*2*30)+30, 85);
                        g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                        g.drawLine((i*2*30)+60, 230, (i*2*30)+30, 230);

                        tag=false;

                    }
                    else{//si le front precedent est est descendant alors ce front est montant
                          g.drawLine(i*2*30, 230, (i*2*30)+30, 230);
                          g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                          g.drawLine((i*2*30)+30, 85, (i*2*30)+60, 85);

                          tag=true;

                    }}

                }

                currentChar[0] = poly.charAt(i);
                g.setColor(Color.black);
                g.drawChars(currentChar, 0, 1, (i*30*2)+20, 245);
                }

    }

    public void Miller(){

          Graphics g =getGraphics();

             char currentChar[] = new char[1];

            poly = nameField.getText();

            g.setColor(Color.gray);
            g.drawLine(0, 85, poly.length()*30*2, 85);
            g.drawLine(0, 158, poly.length()*30*2, 158);
            g.drawLine(0, 230, poly.length()*30*2, 230);
            g.drawString("nV",poly.length(),85);
	    g.drawString("-nV",poly.length(),230);
	    g.drawString("0V",poly.length(),158);


            var = poly.charAt(0);
            boolean tag=true; //true transition , false on absence de transition
            int etat_prec=0;
             g.setColor(Color.red);
            if(poly.charAt(0) == '1'){

                       g.drawLine(0*2*30, 230, (0*2*30)+30, 230);
                       g.drawLine((0*2*30)+30, 85, (0*2*30)+30, 230);
                       g.drawLine((0*2*30)+30, 85, (0*2*30)+60, 85);

                         tag=true; // bit 1 une transition

                        }
              else {// si c un zero alors absence de trasition

                    g.drawLine((0*2*30), 230, (0*2*30)+30, 230);
                    g.drawLine((0*2*30)+60, 230, (0*2*30)+30, 230);

                    tag = false; //bit 0

             }
                currentChar[0] = poly.charAt(0);
                g.setColor(Color.black);
                g.drawChars(currentChar, 0, 1, (0*30*2)+20, 245);

            for (int i=1; i < poly.length(); i++) {

                etat_prec=(int) var;
                var = poly.charAt(i);
                g.setColor(Color.red);

                if(etat_prec == var){
                    if (poly.charAt(i) == '1') {
                    if(tag == true){ //si y a une transition
                    g.drawLine((i*2*30), 85, (i*2*30)+30, 85);
                    g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                    g.drawLine((i*2*30)+60, 230, (i*2*30)+30, 230);

                    tag=false;
                    }
                else  {
                    g.drawLine(i*2*30, 230, (i*2*30)+30, 230);
                    g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                    g.drawLine((i*2*30)+30, 85, (i*2*30)+60, 85);
                    tag=true;

                }
                    }


                        else { //bit égal a 0
                               if(tag==true){//si le front precedent est en transition alors tracer en haut
                                      g.drawLine(i*2*30, 85, (i*2*30)+30, 85);
                                      g.drawLine((i*2*30)+30, 85, (i*2*30)+60, 85);
                                      tag=true;
                               }
                        else if (tag==false){  //si le front precedent est decendant ce font est descendant
                           g.drawLine((i*2*30), 230, (i*2*30)+30, 230);
                           g.drawLine((i*2*30)+60, 230, (i*2*30)+30, 230);

                           tag=false;

                        }
                        }
                        }
                        else{
                            if (poly.charAt(i) == '0') {
                                if(tag==true){ //si le front precedent est montant alors absence de trasition en haut
                                    g.drawLine(i*2*30, 85, (i*2*30)+30, 85);
                                    g.drawLine((i*2*30)+30, 85, (i*2*30)+60, 85);

                                    tag=true;

                                }
                            else if (tag==false){//si le front precedent est descendant alors absence de trasition vers en bas
                                     g.drawLine((i*2*30), 230, (i*2*30)+30, 230);
                                     g.drawLine((i*2*30)+60, 230, (i*2*30)+30, 230);
                                     tag=false;
                            }

                            }

                 else {//l'etat present est 1 et l'etat precedent est 0 renversement
                    if(tag==true){//tracer de haut vers le bas
                        g.drawLine((i*2*30), 85, (i*2*30)+30, 85);
                        g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                        g.drawLine((i*2*30)+60, 230, (i*2*30)+30, 230);

                        tag=false;

                    }
                    else{//tracer de bas vers le haut
                          g.drawLine(i*2*30, 230, (i*2*30)+30, 230);
                          g.drawLine((i*2*30)+30, 85, (i*2*30)+30, 230);
                          g.drawLine((i*2*30)+30, 85, (i*2*30)+60, 85);

                          tag=true;

                    }}


                }

                currentChar[0] = poly.charAt(i);
                g.setColor(Color.black);
                g.drawChars(currentChar, 0, 1, (i*30*2)+20, 245);
                }

    }
}
