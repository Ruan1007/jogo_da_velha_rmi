package jogo.rmi;

class CustomException extends Exception
{
	private static final long serialVersionUID = 4195358804691165427L;

	// Parameterless Constructor
      public CustomException() {}

      // Constructor that accepts a message
      public CustomException(String message)
      {
         super(message);
      }
 }