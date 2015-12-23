
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Arboraiscance des classes : Instructions et Expressions mais aussi Program 
 * Toutes es classes realisant des conditions ,boucles seront ici
 * Chaque nom de fonctions est indicatif. 
 */

abstract class Expression {
    abstract int eval(ValueEnvironment env)
    throws Exception;
 
}


class Int extends Expression {
	private int value;
	public Int(int i) {
		value = i;
	}
	public int eval(ValueEnvironment env)
	throws Exception {
		return value;
	}
	
}


class Var extends Expression {
	private String name;
	public Var(String s) {
		name = s;
	}
	public int eval(ValueEnvironment env)
	throws Exception {
		return env.getValue(name);
	}
	
}



class Sum extends Expression {
	private Expression left, right;
	public Sum(Expression l, Expression r) {
		left = l;
		right = r;
	}
	public int eval(ValueEnvironment env)
	throws Exception {
		return left.eval(env) + right.eval(env);
	}
	
}


class Difference extends Expression {
	private Expression left, right;
	public Difference(Expression l, Expression r) {
		left = l;
		right = r;
	}
	public int eval(ValueEnvironment env)
	throws Exception {
		return left.eval(env) - right.eval(env);
	}
	
}
class Product extends Expression {
	private Expression left, right;
	public Product(Expression l, Expression r) {
		left = l;
		right = r;
	}
	public int eval(ValueEnvironment env)
	throws Exception {
		return left.eval(env) * right.eval(env);
	}
	
}
class Division extends Expression {
	private Expression left, right;
	public Division(Expression l, Expression r) {
		left = l;
		right = r;
	}
	public int eval(ValueEnvironment env)
	throws Exception {
		if(right.eval(env)==0){
			throw new Divisionzero() ;
		}
		return left.eval(env) / right.eval(env);
	}
	
}

class Program {
	private Instruction first;
	private Program rest;
	public Program(Instruction i, Program p) {
		first = i;
		rest = p;
	}
	public void run(ValueEnvironment env) 
	throws Exception {
		if (first != null) {
		
			first.exec(env);
			rest.run(env);
		}
	}

}

abstract class Instruction {
	abstract void exec(ValueEnvironment env)
	throws Exception;
	
}


class Pinceau extends Instruction{
	public boolean b ;

	
	public Pinceau(boolean bool){
		this.b=bool;
	}



	public void exec (ValueEnvironment env){
	//changer le boolean en mode dessin
		
	}
}

class Epaisseur extends Instruction{
	float a =3f ;
	Expression exp ;
	public Epaisseur(Expression exp){
		this.exp = exp;
	}
	public Epaisseur(){}
	public Epaisseur(float a){
		this.a=a;
	}

	public void exec (ValueEnvironment env) throws Exception{
	 a=(float)(exp.eval(env));
	}
}



class Avance extends Instruction{
	Expression exp ;
	Pinceau pin ;
	Epaisseur ep ;
	Coul cou ;
	Boolean a ;
	static ArrayList<Integer> ins= new ArrayList<Integer>() ;
	static ArrayList<Boolean> ins2= new ArrayList<Boolean>() ;
	static ArrayList<Boolean> cat= new ArrayList<Boolean>() ;
	static ArrayList<Float> epais= new ArrayList<Float>() ;
	static ArrayList<ConteneurC> cont = new ArrayList<ConteneurC>();
	
	public Avance (Expression exp , Pinceau pin,boolean a , Epaisseur e ,Coul cou){
		this.exp =exp ;
		this.pin=pin;
		this.a=a;
		this.ep=e ;
		this.cou=cou ;
	}
	
	
	
		
	public void exec (ValueEnvironment env) throws Exception{
		//Ajoute a la liste des instructions la valeur du mouvement
			ins.add(exp.eval(env));
			cat.add(a);
			ins2.add(pin.b);
			epais.add(ep.a);
			cont.add(cou.conte);
	}

}

class Coul extends Instruction{
	Expression exp;
	Expression exp2;
	Expression exp3;
	ConteneurC conte =new ConteneurC() ;
	
	public Coul (){
		conte = new ConteneurC(0,0,0);
	}
	
	public Coul (Expression exp , Expression exp2 ,Expression exp3){
		this.exp=exp;
		this.exp2=exp2;
		this.exp3=exp3;
		
	}
	
	
	public void exec (ValueEnvironment env) throws Exception{
	
		 conte = new ConteneurC(exp.eval(env), exp2.eval(env),exp3.eval(env) );
	}
}

class ConteneurC {
	/*
	 * COuleur RGB
	 */
	int r =0; 
	int g =0 ;
	int b =0;
	public ConteneurC(){}
	
	public ConteneurC(int r, int g ,int b) {
		this.r=r;
		this.g=g;
		this.b=b;
		if(r>254 || g >254 || b > 254){ System.out.println("EXCEPTION :Couleur");    System.exit(1);} 
		
	}
}

class Declaration extends Instruction {
	private String varName;
	public Declaration(String s) {
		varName = s;
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		env.addVariable(varName);
	}

}
class Assignment extends Instruction {
	private String varName;
	private Expression exp;
	public Assignment(String s, Expression e) {
		varName = s;
		exp = e;
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		env.setVariable(varName, exp.eval(env));
	}
	
}
class Print extends Instruction {
	private Expression exp;
	int entier ;
	public Print(Expression e) {
		exp = e;
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		entier = exp.eval(env);
		System.out.println(exp.eval(env));
	}
	
}


class Condition extends Instruction{

	private Expression exp;
	private Program SI;
	private Program SINON;
	
	public Condition(Expression exp , Program SI , Program SINON){
		this.exp=exp;
		this.SI=SI;
		this.SINON=SINON;
	}
	
	public void exec(ValueEnvironment env) throws Exception {
		if(exp.eval(env)!=0){
			SI.run(env);
		}else if(SINON != null){
			SINON.run(env);
		}
		
	}
}




class Loop2 extends Instruction{
	Expression exp ;
	Program prog ;
	
	public Loop2(Expression exp , Program prog){
		this.exp=exp ;
		this.prog=prog;
	}
	
	
	public void exec(ValueEnvironment env) throws Exception {
		while(exp.eval(env) != 0){
			prog.run(env);
		}
	}
	
}




class Loop extends Instruction {
	private Expression exp;
	private Program prog;
	private static int loops = 0;
	public Loop(Expression e, Program p) {
		exp = e;
		prog = p;
	}
	public void exec(ValueEnvironment env) throws Exception {
		int n = exp.eval(env);
		for (int i = 0; i < n; i++) {
			prog.run(env);
		}
	}
	
}

class Debut extends Instruction{
	Program prog ; 
	public Debut(Program prog ){
		this.prog=prog ;
	}
	@Override
	void exec(ValueEnvironment env) throws Exception {
		// TODO Auto-generated method stub
		prog.run(env) ;
	}
	

}


class ValueEnvironment extends HashMap<String, Integer> {
	public ValueEnvironment() {
		super();
	}
	public void addVariable(String name) 
	throws Exception {
		if (this.containsKey(name)) 
			throw new VariableTwiceDefinedException(name);
		this.put(name, null);
	}
	public void setVariable(String name, int value) 
	throws Exception {
		if (!this.containsKey(name))
			throw new VariableNotDefinedException(name);
		this.put(name, value);
	}
	public int getValue(String name) 
	throws Exception {
		if (!this.containsKey(name))
			throw new VariableNotDefinedException(name);
		Integer value = this.get(name);
		if (value == null)
			throw new VariableNotInitializedException(name);
		return value;
	}
}


//suplemmentaire


class Whilee extends Instruction{
	Expression exp ;
	Expression exp2 ;
	char a ;
	Program prog;
	
	
	
	public Whilee(Expression exp, Expression exp2, char a, Program prog) {
		this.exp = exp;
		this.exp2 = exp2;
		this.a = a;
		this.prog = prog;
	}



	public void exec(ValueEnvironment env) throws Exception {
		if(a=='>'){
			while(exp.eval(env) >exp2.eval(env)){
				prog.run(env);
			}
		}else if(a=='<'){
			while(exp.eval(env) < exp2.eval(env)){
				prog.run(env);
			}
		}else if(a=='%'){
			while(exp.eval(env) % exp2.eval(env) !=0){
				prog.run(env);
			}
		}else if(a=='='){
			while(exp.eval(env) == exp2.eval(env) ){
				prog.run(env);
			}
		}
	}
}

class Condi extends Instruction{
	Expression exp ;
	Expression exp2 ;
	char a ;
	Program prog;
	Program prog2 ;
	
	public Condi(Expression exp, Expression exp2, char a, Program prog,
			Program prog2) {
		this.exp = exp;
		this.exp2 = exp2;
		this.a = a;
		this.prog = prog;
		this.prog2 = prog2;
	}

	public void exec(ValueEnvironment env) throws Exception {
		if(a=='>'){
			if(exp.eval(env) >exp2.eval(env))
					prog.run(env);
			else if(prog2 != null){
					prog2.run(env);
			}
		}else if(a=='<'){
			if(exp.eval(env) <  exp2.eval(env))
				prog.run(env);
			else if(prog2 != null){
				prog2.run(env);
		}
		}else if(a=='%'){
			if(exp.eval(env) %exp2.eval(env) == 0)
				prog.run(env);
			else if(prog2 != null){
				prog2.run(env);
			}
		}else if(a=='='){
			if(exp.eval(env) == exp2.eval(env) )
				prog.run(env);
			else if(prog2 != null){
				prog2.run(env);
			}
		}
	}
}
