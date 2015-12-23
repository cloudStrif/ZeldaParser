%%
%public
%class Lexer
%unicode
%type Token
%line
%column

%{
	
%}

%yylexthrow{
	LexerException
%yylexthrow}

blank = "\n" | "\r" | " " | "\t"
int   =[1-9][0-9]* | "0"
variable = [a-z][a-zA-Z0-9]*

%%

<YYINITIAL> {
   ";"		{return new Token(Sym.CONCAT);}
   "VAR"	{return new Token(Sym.VAR);}
   "="		{return new Token(Sym.EQ);}
   "PRINT"	{return new Token(Sym.PRINT);}
   "FOR"	{return new Token(Sym.FOR);}
   "TIMES"		{return new Token(Sym.TIMESDO);}
   "AVANCE"		{return new Token(Sym.AVANCE);}
 "TOURNE"		{return new Token(Sym.TOURNE);}
 "DEBUT"		{return new Token(Sym.DEBUT);}
 "FIN"		{return new Token(Sym.FIN);} 
 "BASPINCEAU"		{return new Token(Sym.BASPINCEAU);}
 "HAUTPINCEAU"		{return new Token(Sym.HAUTPINCEAU);}
"EPAISSEUR"         {return new Token(Sym.EPAISSEUR);}
"COULEUR"        {return new Token(Sym.COULEUR);}
"SI"        {return new Token(Sym.SI);}
"ALORS"        {return new Token(Sym.ALORS);}
"SINON"        {return new Token(Sym.SINON);}
"TANTQUE"        {return new Token(Sym.TANTQUE);}
"FAISONS"        {return new Token(Sym.FAISONS);}


"<"        {return new Token(Sym.INFE);}
">"        {return new Token(Sym.SUPE);}
"WHILE"        {return new Token(Sym.WHILE);}
"IF"        {return new Token(Sym.IF);}
"THEN"        {return new Token(Sym.THEN);}
"ELSE"        {return new Token(Sym.ELSE);}
"MOD"        {return new Token(Sym.MODU);}


","        {return new Token(Sym.VIRGULE);}
"{"		{return new Token(Sym.LACC);}
   "}"		{return new Token(Sym.RACC);}
   {variable}	{return new VarToken(Sym.VARIABLE,yytext());}
   "("		{return new Token(Sym.LPAR);}
   ")"		{return new Token(Sym.RPAR);}
   {int}	{return new IntToken(Sym.INT,Integer.parseInt(yytext()));}
   "+"		{return new Token(Sym.PLUS);}
   "-"		{return new Token(Sym.MINUS);}
   "/"		{return new Token(Sym.DIV);}
   "*"		{return new Token(Sym.TIMES);}
   {blank}	{}
   <<EOF>>	{return new Token(Sym.EOF);}
   [^]		{throw new LexerException(yyline, yycolumn);}
}



