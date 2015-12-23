
import java.io.*;
import java.util.*;

class Parser {
	
	/*
	 * PROJET ADS4 
	 * 
	 * Grammaire : Programme -> nontermCode2 nontermCode
	 * 				nontermCode2 -> VAR identificateur  ; | Rien -> nontermInst2()
	 * 				nontermCode -> VARIABLE  = nontermExp() | HAUTPINCEAU | BASPINCEAU | AVANCE = nontermExp() | TOURNE nontermExp() |PRINT nontermExp()
	 * 				| COULEUR (nontermExp() , nontermExp() ,nontermExp() ) ; | EPAISSEUR nontermExp() |
	 * 				TANTQUE nontermExp() FAISONS{nontermProg() } | SI nontermExp() ALORS{nontermProg() ;} nonterm() ;||DEBUT BlocInstruction(Debut) FIN ;
	 * 				FOR nontermExp() TIMES{nontermProg() ;} | WHILE (nontermExp() >|<|MOD nontermExp() ) {nontermProg(); } | IF(nontermExp() >|<|MOD  nontermExp())
	 * 				THEN{nontermProg() ;} nonterm2 ; 
	 * 				nonterm ->SINON nontermPorg() |rien
	 * 				nonterm2 -> ELSE nontermProg() |rien
	 * 				
	 * 			 nonTermExp-> nombre nonTermExpSuite | identificateur nonTermExpSuite | (nonTermExp) nonTermExpSuite
	 * 			nonTermExpSuite->operator nonTermExpSuite | rien
	 * 
	 * 				operateur -> + expr | -expr |*expr |/expr
	 * 				BlocInstruction -> Programme
	 * 				nontermsup -> > | < | MOD |=
	 */


    protected LookAhead1 reader;
    //Avance et Tourne 
    Avance av ;
    //Initiallement on est a false A ameliorer.Permet de savoir si on dessine ou non
    Pinceau pinceau =new Pinceau(false);
    Epaisseur ep = new Epaisseur(3f) ;
    Coul coola = new Coul() ;
    
    //error
    String error = "\n";
    
    public Parser(LookAhead1 r) {
	   reader=r;
    }

    public Program nontermCode() throws Exception {
    	Program prog = nontermProg();
    	 reader.eat(Sym.EOF);
        return prog;
    }

    
    
    
    
    
    public Program nontermCode2() throws Exception{
    	 Program prog = nontermProg2();
         //reader.eat(Sym.EOF);
         return prog;
    }
    
    public Program nontermProg2() throws Exception{
    	 if (reader.check(Sym.VAR)  ){
    		 
    		  Instruction i = nontermInst2();
              Program p = nontermProg2();
              return new Program(i, p);
    	 }else{
    		 return new Program(null, null);
    	 }
    }
    
    
    
    
    
    
    public Program nontermProg()
    throws Exception {
        if (/*reader.check(Sym.VAR) ||*/ reader.check(Sym.VARIABLE) ||
            reader.check(Sym.PRINT) || reader.check(Sym.FOR) || 
            reader.check(Sym.BASPINCEAU) || reader.check(Sym.HAUTPINCEAU) ||
            reader.check(Sym.TOURNE) || reader.check(Sym.AVANCE)
            || reader.check(Sym.EPAISSEUR) ||  reader.check(Sym.SI)
            || reader.check(Sym.TANTQUE) || reader.check(Sym.COULEUR) || 
            reader.check(Sym.WHILE) || reader.check(Sym.IF) || reader.check(Sym.DEBUT)) {
            Instruction i = nontermInst();
            Program p = nontermProg();
            return new Program(i, p);
        } else {
            return new Program(null, null);
        }
    }

    
    
    
    public Instruction nontermInst2() throws Exception{
        if (reader.check(Sym.VAR)) {
            reader.eat(Sym.VAR);
            String varName = reader.getStringValue();
            reader.eat(Sym.VARIABLE);
            reader.eat(Sym.CONCAT);
            return new Declaration(varName);
        }
       
        error =error+reader.getString()+error ;
        throw new ParserException(error+reader.getString()+error);
    }
    
    
    
    
    
    public Instruction nontermInst()
    throws Exception { 
    	  if (reader.check(Sym.DEBUT)) {
    		  reader.eat(Sym.DEBUT) ;
    		  Program prog = nontermProg();
    		  reader.eat(Sym.FIN) ; 
    		
    		  return new Debut(prog);
    	  }
        if (reader.check(Sym.VARIABLE)) {
            String varName = reader.getStringValue();
            reader.eat(Sym.VARIABLE);
            reader.eat(Sym.EQ);
            Expression exp = nontermExp();
            reader.eat(Sym.CONCAT);
            return new Assignment(varName, exp);
        }
        if (reader.check(Sym.PRINT)) {
            reader.eat(Sym.PRINT);
            Expression exp = nontermExp();
            reader.eat(Sym.CONCAT);
            return new Print(exp);
        }  
        if (reader.check(Sym.FOR)) {
            reader.eat(Sym.FOR);
            Expression exp = nontermExp();
            reader.eat(Sym.TIMESDO);
            reader.eat(Sym.LACC);
            Program prog = nontermProg();
            reader.eat(Sym.RACC);
            return new Loop(exp, prog);
        }   
	
	if(reader.check(Sym.HAUTPINCEAU)){
		reader.eat(Sym.HAUTPINCEAU);
		reader.eat(Sym.CONCAT);
		pinceau = new Pinceau(false);
		return pinceau ;
	}
	if(reader.check(Sym.BASPINCEAU)){
		reader.eat(Sym.BASPINCEAU);
		reader.eat(Sym.CONCAT);
	
		pinceau = new Pinceau(true);
		return pinceau ;
    }
	if(reader.check(Sym.AVANCE)){
		reader.eat(Sym.AVANCE);
		 Expression exp = nontermExp();
		reader.eat(Sym.CONCAT);
		 av = new Avance(exp,pinceau,false,ep ,coola);
		return av ;
	}
	if(reader.check(Sym.TOURNE)){
		reader.eat(Sym.TOURNE);
		 Expression exp = nontermExp();
		 
		reader.eat(Sym.CONCAT);
		 av = new Avance(exp,pinceau,true,ep,coola);
		return av ;
	}
	if(reader.check(Sym.EPAISSEUR)){
		reader.eat(Sym.EPAISSEUR);
		Expression exp = nontermExp();
		reader.eat(Sym.CONCAT);
		ep = new Epaisseur(exp);
		return ep ;
	}	

	//VERSION AVEC SI ALORS SINON
	/*	 if(reader.check(Sym.SI)){
		reader.eat(Sym.SI);
		Expression exp = nontermExp();
		reader.eat(Sym.ALORS);
		 reader.eat(Sym.LACC);
		 Program prog1 = nontermProg();
		 reader.eat(Sym.RACC);
		 reader.eat(Sym.SINON);
		 reader.eat(Sym.LACC);
		 Program prog2 = nontermProg();
		 reader.eat(Sym.RACC);
		return new Condition(exp ,prog1,prog2);
		
	}*/
	
	//VERSION AVEC SI ALORS SINON       OU  SI ALORS
	if(reader.check(Sym.SI)){
		reader.eat(Sym.SI);
		Expression exp = nontermExp();
		reader.eat(Sym.ALORS);
		 reader.eat(Sym.LACC);
		 Program prog1 = nontermProg();
		 reader.eat(Sym.RACC);
		 Program prog2 = nonterm();
		 if(prog2 != null)
			 reader.eat(Sym.RACC);
			return new Condition(exp ,prog1,prog2);
	}
	
	if(reader.check(Sym.WHILE)){
		reader.eat(Sym.WHILE);
		 reader.eat(Sym.LPAR);
		 Expression exp = nontermExp() ;
		char exp2 = nontermsup();
		 Expression exp3 = nontermExp() ;
		 reader.eat(Sym.RPAR);
		 reader.eat(Sym.LACC);
		 Program prog = nontermProg();
		 reader.eat(Sym.RACC);
		 return new Whilee(exp,exp3,exp2,prog);
	}
	
	if(reader.check(Sym.IF)){
		reader.eat(Sym.IF);
		reader.eat(Sym.LPAR);
		Expression exp = nontermExp() ;
		char exp2 = nontermsup();
		Expression exp3 = nontermExp() ;
		reader.eat(Sym.RPAR);
		reader.eat(Sym.THEN);
		reader.eat(Sym.LACC);
		 Program prog = nontermProg();
		reader.eat(Sym.RACC);
		Program prog2 = nonterm2() ;
		if(prog2 != null)
			 reader.eat(Sym.RACC);
		return new Condi(exp , exp3 ,exp2 ,prog ,prog2);
		
	}
	
	
	if(reader.check(Sym.TANTQUE)){
		reader.eat(Sym.TANTQUE);
		Expression exp = nontermExp() ;
		reader.eat(Sym.FAISONS);
        reader.eat(Sym.LACC);
        Program prog = nontermProg();
        reader.eat(Sym.RACC);
        return new Loop2(exp,prog);
	}
	if(reader.check(Sym.COULEUR)){
		reader.eat(Sym.COULEUR);
		reader.eat(Sym.LPAR);
		Expression exp = nontermExp();
		reader.eat(Sym.VIRGULE);
		Expression exp2 = nontermExp();
		reader.eat(Sym.VIRGULE);
		Expression exp3 = nontermExp();
		reader.eat(Sym.RPAR);
		reader.eat(Sym.CONCAT);
		coola = new Coul(exp ,exp2,exp3);
		return coola ;
	}
	
		error =error+reader.getString()+error ;
        throw new ParserException(error+reader.getString()+error);
    }

    public Program nonterm2() throws Exception{
    	if(reader.check(Sym.ELSE)){
    		reader.eat(Sym.ELSE);
    		reader.eat(Sym.LACC);
    		return nontermProg() ;
    	}else
    		return null;
    }
    
    public Program nonterm() throws Exception{
    	if(reader.check(Sym.SINON)){
    		reader.eat(Sym.SINON);
    		reader.eat(Sym.LACC);
    		return nontermProg() ;
    	}else
    		return null;
    }
   
    public Expression nontermExp() 
    throws Exception {
        if (reader.check(Sym.LPAR)) {
            reader.eat(Sym.LPAR);
            Expression exp = nontermExp();
            exp = nontermplus(exp);
            reader.eat(Sym.RPAR);
	  	
            return exp;
        }
        if (reader.check(Sym.INT)) {
        	int n = reader.getIntValue();
            reader.eat(Sym.INT);
            Expression exp = new Int(n);
           // return new Int(n);  
        	return nontermplus(exp);
        }
        if (reader.check(Sym.VARIABLE)) {
            String s = reader.getStringValue();
            reader.eat(Sym.VARIABLE);
            Expression exp = new Var(s) ;
           // return new Var(s);
            return nontermplus(exp);
        }
        error=error+reader.getString()+error;
        throw new ParserException(error+reader.getString()+error);
    }
    
    
    public Expression nontermplus(Expression e) throws Exception{
    	if(reader.check(Sym.PLUS) || reader.check(Sym.MINUS) ||reader.check(Sym.TIMES) || reader.check(Sym.DIV)){
    		Expression e2 = nontermExpSuite(e);
    		return nontermplus(e2);
    	}
    	return e ;
    }
    
    
    public char nontermsup() throws Exception{
    	 if(reader.check(Sym.SUPE)){
			 reader.eat(Sym.SUPE);
			 return '>' ;
		 }
		 if(reader.check(Sym.INFE)){
			reader.eat(Sym.INFE);
			 return '<' ;
		 }
		 if(reader.check(Sym.MODU)){
			 reader.eat(Sym.MODU);
			 return '%' ;
		 }
		 if(reader.check(Sym.EQ)){
			 reader.eat(Sym.EQ);
			 return '=' ;
		 }
		 
		  error= error+reader.getString()+error;
	       throw new ParserException(error+reader.getString()+error);
		 
    }

    public Expression nontermExpSuite(Expression beginning) 
    throws Exception {
        if (reader.check(Sym.PLUS)) {
            reader.eat(Sym.PLUS);
            Expression end = nontermExp();
            return new Sum(beginning, end);
        }
        if (reader.check(Sym.MINUS)) {
            reader.eat(Sym.MINUS);
            Expression end = nontermExp();
            return new Difference(beginning, end);
        }
        if (reader.check(Sym.TIMES)) {
            reader.eat(Sym.TIMES);
            Expression end = nontermExp();
            return new Product(beginning, end);
        }
        if (reader.check(Sym.DIV)) {
            reader.eat(Sym.DIV);
            Expression end = nontermExp();
            return new Division(beginning, end);
        }
        error= error+reader.getString()+error;
        throw new ParserException(error+reader.getString()+error);
    }
}

