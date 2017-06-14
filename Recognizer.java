import java.io.*;
import java.util.Scanner;

//--------------------------------------------
public class Recognizer
{
  static String inputString;
  static int index = 0;
  static int errorflag = 0;

  private char token()
  { return(inputString.charAt(index)); }

  private void advancePtr()
  { if (index < (inputString.length()-1)) index++; }

  private void match(char T)
  { if (T == token()) advancePtr(); else error(); }

  private void error()
  {
    System.out.println("error at position: " + index);
    errorflag = 1;
    advancePtr();
  }

  private void block()
  {
    match('B');
    //if (token == 'B') match('B');
    while (token() == 'A' || token() == 'I' || token() == 'W' || token() == 'R' || token() == 'O') statemt();
    match('E');
    //if (token == 'E') match('E');
    while (token() == 'D') match('D');
  }

  private void statemt()
  {
    if (token() == 'A') asignmt();
    else if (token() == 'I') ifstmt();
    else if (token() == 'W') whil();
    else if (token() == 'R' || token() == 'O') inpout();
    else if (token() == 'B') block();
  }

  private void asignmt()
  {
    match('A');
    //if (token == 'A') match('A');
    ident();
    match('~');
    //if (token == '~') match('~');
    exprsn();
  }

  private void ifstmt()
  {
    match('I');
    //if (token == 'I') match(token);
    comprsn();
    match('T');
    //if (token == 'T') match(token);
    block();
    while (token() == 'L') { match('L'); block(); }
  }

  private void whil()
  {
     match('W');
     comprsn();
     block();
  }

  private void inpout()
  {
     iosym();
     ident();
     while (token() == ',') { match(','); ident(); }
  }

  private void comprsn()
  {
    match('(');
    oprnd();
    opratr();
    oprnd();
    match(')');
  }

  private void exprsn()
  {
    factor();
    while (token() == '+' || token() == '-') {
        factor(); sumop();
    }
  }

  private void factor()
  {
    oprnd();
    while (token() == '*' || token() == '/') {
        oprnd(); prodop();
    }
  }

  private void oprnd()
  {
    if (token() == '0' || token() == '1') integer();
    else if (token() == 'X' || token() == 'Y' || token() == 'Z') { ident(); }
    else if (token() == '(') { match('('); exprsn(); match(')'); }
  }

  private void ident()
  {
    letter();
    while (token() == '0' || token() == '1' || token() == 'X' || token() == 'Y' || token() == 'Z') { chr(); }
  }

  private void chr()
  {
    if (token() == 'X' || token() == 'Y' || token() == 'Z') letter();
    else if (token() == '0' || token() == '1') digit();
  }

  private void integer()
  {
    digit();
    while (token() == '0' || token() == '1') digit();

  }

  private void digit()
  {
    if ((token() == '0') || (token() == '1'))  match(token());
    else error();
  }

  private void letter()
  {
    if ((token() == 'X') || (token() == 'Y') || (token() == 'Z')) match(token());
    else error();
  }

  private void iosym()
  {
    if ((token() == 'R') || (token() == 'O')) match(token());
    else error();
  }

  private void opratr()
  {
    if ((token() == '<') || (token() == '=') || (token() == '>') || (token() == '!')) match(token());
    else error();
  }

  private void sumop()
  {
    if ((token() == '+') || (token() == '-')) match(token());
    else error();
  }

  private void prodop()
  {
    if ((token() == '*') || (token() == '/')) match(token());
    else error();
  }

  private void start()
  {
    block();
    match('$');

    if (errorflag == 0)
      System.out.println("legal." + "\n");
    else
      System.out.println("errors found." + "\n");
  }

public static void main (String[] args) throws IOException
  {
    Recognizer rec = new Recognizer();

    Scanner input = new Scanner(System.in);

    System.out.print("\n" + "enter an expression: ");
    inputString = input.nextLine();

    rec.start();
  }
}
