

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panneau extends JPanel{

  
	
	/*
     * Parametres lié a la tortue areporter si il le faut
     * gestion de l'affichage dans unJPanel
     */
    int x = 0 ;
    int y2 =0;
    int y =0 ;
    int angular =0 ;
    boolean pince =false;
    ArrayList<Float> pincea = new ArrayList<Float>() ;
    //epaisseur de la taille du crayon
    float ep =3;
    
    int depart =0 ;
    int depart2 =0 ;
    ArrayList<LineT> line = new ArrayList<LineT>();
	
	
	
    int orientation =0 ;
    int avance = 1 ;
	
	
    boolean affiche =false;
    
    boolean affiche2 =false;
    int anglerot =0 ;
    Fenetre fen ;
    int niveau =0 ;
    
    
    public void pause (int a){
	//Utilisé pour l'animation 
    	try {
    		Thread.sleep(a);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}	
    }
    
   
	  
    public Panneau(Fenetre fen){
	this.fen=fen ;
    }

	  
    public void paintComponent(Graphics g){
	
	
	print(g);
	
    }
	
    int ii =0 ;
    int ii2 =0 ;
    public void print(Graphics g){
    	if(niveau==1){
    		g.setColor(new Color(22, 184, 78));
    		g.fillRect(0,0,this.getWidth(),this.getHeight());
    	//Les sprites sont tirés de The Legend of Zelda
    	ImageIcon imageIcon9 = new ImageIcon("img2/logo.png");
		Image w9 = imageIcon9.getImage();   								
		g.drawImage(w9,this.getWidth()-150,this.getHeight()-60,this);
    	//arbre de decoration
    	ImageIcon image144 = new ImageIcon("img2/arb2.png");
    	Image d144 = image144.getImage();   							
    	for(int lx = 0; lx <this.getWidth() ;lx =lx +50)
    	g.drawImage(d144,lx,10,this);	
    	for(int ly = 50; ly <this.getHeight() ;ly =ly +50)
   		g.drawImage(d144,this.getWidth()-50,ly,this);	
    
    	
    	//uzumaki(g);
    	
	
    	
			

    	
    	
    	
	   

	    //	g.drawLine(depart, depart2, x, (550-y));
	    Graphics2D gd = (Graphics2D)g;
	    for(int i=0 ; i < line.size() ; i++){
	    	if(line.get(i).cont != null)
	    		g.setColor(new Color(line.get(i).cont.r ,line.get(i).cont.g,line.get(i).cont.b));
	    	else
	    		g.setColor(Color.BLACK);
	    	 //	line.get(i).taille = pincea.get(i);
	    	if(line.get(i).taille > 0){
	    		Stroke stroke = new BasicStroke(line.get(i).taille, BasicStroke.CAP_SQUARE,
	    				BasicStroke.JOIN_MITER, 10.0f);
	    		gd.setStroke(stroke);
	    	}else{
	    		Stroke stroke = new BasicStroke(3f, BasicStroke.CAP_SQUARE,
	    				BasicStroke.JOIN_MITER, 10.0f);
	    		gd.setStroke(stroke);	
	    	}
			
				gd.drawLine(line.get(i).x ,line.get(i).y ,line.get(i).x1 ,(550-line.get(i).y1) );
			if(pince)		
				gd.drawLine(ii,ii2,x,(550-y));
		
	    }
					
   
		
    	/*
    	 * Gestion de l'animation de la tortue
    	 */
 
			
	if(affiche){
	    g.setColor(Color.RED);
	    g.drawString("ROTATION DE "+anglerot +" DEGRES !",x+20,(550-y)+20);
	}
	if(affiche2){
	    g.setColor(Color.RED);
	    g.drawString("EXCEPTION :TORTUE SORTI !",250,250);
	}
			
	if(orientation== 0){
	    switch(avance){
	    case 1 :
										
		ImageIcon imageIcon = new ImageIcon("img/d1.gif");
		Image w = imageIcon.getImage();   
											
		g.drawImage(w,x,550-y,this);
		//  g.drawImage(w,x,y,this);
		break ;
										
										
	    case 2 :
											
		ImageIcon image = new ImageIcon("img/d2.gif");
		Image d = image.getImage();   
											
		g.drawImage(d,x,550-y,this);
		break ;
										
	    case 3 :
										
		ImageIcon image2 = new ImageIcon("img/d3.gif");
		Image d1 = image2.getImage();   
						
		g.drawImage(d1,x,550-y,this);
							  
		break ;
									
	    case 4 :
								
		ImageIcon image3 = new ImageIcon("img/d4.gif");
		Image d2 = image3.getImage();   
						
		g.drawImage(d2,x,550-y,this);
							  
		break ;
	    }
	}
	if(orientation== 1){
	    switch(avance){
	    case 1 :
										
		ImageIcon imageIcon = new ImageIcon("img/l4.gif");
		Image w = imageIcon.getImage();   
											
		g.drawImage(w,x,550-y,this);
		//  g.drawImage(w,x,y,this);
		break ;
										
										
	    case 2 :
											
		ImageIcon image = new ImageIcon("img/l3.gif");
		Image d = image.getImage();   
											
		g.drawImage(d,x,550-y,this);
		break ;
										
	    case 3 :
										
		ImageIcon image2 = new ImageIcon("img/l2.gif");
		Image d1 = image2.getImage();   
						
		g.drawImage(d1,x,550-y,this);
							  
		break ;
									
	    case 4 :
								
		ImageIcon image3 = new ImageIcon("img/l1.gif");
		Image d2 = image3.getImage();   
						
		g.drawImage(d2,x,550-y,this);
							  
		break ;
	    }
	}
					 
	if(orientation== 2){
	    switch(avance){
	    case 1 :
										
		ImageIcon imageIcon = new ImageIcon("img/b1.gif");
		Image w = imageIcon.getImage();   
											
		g.drawImage(w,x,550-y,this);
		//  g.drawImage(w,x,y,this);
		break ;
										
										
	    case 2 :
											
		ImageIcon image = new ImageIcon("img/b2.gif");
		Image d = image.getImage();   
											
		g.drawImage(d,x,550-y,this);
		break ;
										
	    case 3 :
										
		ImageIcon image2 = new ImageIcon("img/b3.gif");
		Image d1 = image2.getImage();   
						
		g.drawImage(d1,x,550-y,this);
							  
		break ;
									
	    case 4 :
								
		ImageIcon image3 = new ImageIcon("img/b4.gif");
		Image d2 = image3.getImage();   
						
		g.drawImage(d2,x,550-y,this);
							  
		break ;
	    }
	}
	if(orientation== 3){
	    switch(avance){
	    case 1 :
										
		ImageIcon imageIcon = new ImageIcon("img/h1.gif");
		Image w = imageIcon.getImage();   
											
		g.drawImage(w,x,550-y,this);
		//  g.drawImage(w,x,y,this);
		break ;
										
										
	    case 2 :
											
		ImageIcon image = new ImageIcon("img/h2.gif");
		Image d = image.getImage();   
											
		g.drawImage(d,x,550-y,this);
		break ;
										
	    case 3 :
										
		ImageIcon image2 = new ImageIcon("img/h3.gif");
		Image d1 = image2.getImage();   
						
		g.drawImage(d1,x,550-y,this);
							  
		break ;
									
	    case 4 :
								
		ImageIcon image3 = new ImageIcon("img/h4.gif");
		Image d2 = image3.getImage();   
						
		g.drawImage(d2,x,550-y,this);
							  
		break ;
	    }
	}
	//Tourner
	if(orientation== 4){
	    switch(avance){
	    case 1 :
										
		ImageIcon imageIcon = new ImageIcon("img/e2.gif");
		Image w = imageIcon.getImage();   
											
		g.drawImage(w,x,550-y,this);
		//  g.drawImage(w,x,y,this);
		break ;
										
										
	    case 2 :
											
		ImageIcon image = new ImageIcon("img/e3.gif");
		Image d = image.getImage();   
											
		g.drawImage(d,x,550-y,this);
		break ;
										
	    case 3 :
										
		ImageIcon image2 = new ImageIcon("img/e4.gif");
		Image d1 = image2.getImage();   
						
		g.drawImage(d1,x,550-y,this);
							  
		break ;
									
	    case 4 :
								
		ImageIcon image3 = new ImageIcon("img/e5.gif");
		Image d2 = image3.getImage();   
						
		g.drawImage(d2,x,550-y,this);
							  
		break ;
							    
	    case 5:
									
		ImageIcon image5 = new ImageIcon("img/ed4.gif");
		Image d5 = image5.getImage();   
								
		g.drawImage(d5,x,550-y,this);
		break ;
							
	    case 6 :

		ImageIcon image6 = new ImageIcon("img/ed3.gif");
		Image d6 = image6.getImage();   
								
		g.drawImage(d6,x,550-y,this);
		break ;
								
	    case 7 :

		ImageIcon image7 = new ImageIcon("img/ed2.gif");
		Image d7 = image7.getImage();   
								
		g.drawImage(d7,x,550-y,this);
		break ;
							
	    case 8 :

		ImageIcon image8 = new ImageIcon("img/eh1.gif");
		Image d8 = image8.getImage();   
								
		g.drawImage(d8,x,550-y,this);
		break ;
								
	    case 9 :

		ImageIcon image9 = new ImageIcon("img/eh2.gif");
		Image d9 = image9.getImage();   
								
		g.drawImage(d9,x,550-y,this);
		break ;
								
	    case 10 :

		ImageIcon image10 = new ImageIcon("img/eh3.gif");
		Image d10 = image10.getImage();   
									
		g.drawImage(d10,x,550-y,this);
		break ;
	    case 11 :

		ImageIcon image11 = new ImageIcon("img/eh4.gif");
		Image d11 = image11.getImage();   
										
		g.drawImage(d11,x,550-y,this);
		break ;
	    case 12 :

		ImageIcon image12 = new ImageIcon("img/eh5.gif");
		Image d12 = image12.getImage();   
										
		g.drawImage(d12,x,550-y,this);
		break ;
	    case 13 :

		ImageIcon image13 = new ImageIcon("img/eg2.gif");
		Image d13 = image13.getImage();   
										
		g.drawImage(d13,x,550-y,this);
		break ;
	    case 14 :

		ImageIcon image14 = new ImageIcon("img/eg3.gif");
		Image d14 = image14.getImage();   
										
		g.drawImage(d14,x,550-y,this);
		break ;
	    case 15 :

		ImageIcon image1a = new ImageIcon("img/eg4.gif");
		Image d1a = image1a.getImage();   
										
		g.drawImage(d1a,x,550-y,this);
		break ;
	    case 16 :

		ImageIcon image16 = new ImageIcon("img/eg5.gif");
		Image d16 = image16.getImage();   
										
		g.drawImage(d16,x,550-y,this);
		break ;
	    }
	}
					 
    	}else if(niveau == 0){
    		
    		ImageIcon image16 = new ImageIcon("img2/logo2.jpeg");
    		Image d16 = image16.getImage();   
    										
    		g.drawImage(d16,0,0,this.getWidth(),this.getHeight(),this);
    		g.setFont(new Font("TimesRoman", Font.BOLD, 17));
    		g.setColor(Color.CYAN);
    		g.drawString("Projet Analyse de Donnees Structure ",170,y2);
    		g.drawString("Presente par Patrick Chen ",170,y2+20);
    	}
				
				
	
    }
}

