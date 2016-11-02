package baseclasses

/**
 Class to hold different with different numbers and suites.
 */
object Card {
  private val rankNames = Array("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
  private val suitNames = Array("C", "H", "S", "D")
  
  val cardFaces = Array(
    "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC",
    "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH",
    "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS",
    "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD")

  val CLUBS = 1;
  val HEARTS = 2;
  val SPADES = 3;
  val DIAMONDS = 4;
  
  val ACE = 1;
  val TWO = 2;
  val THREE = 3;
  val FOUR = 4;
  val FIVE = 5;
  val SIX = 6;
  val SEVEN = 7;
  val EIGHT = 8;
  val NINE = 9;
  val TEN = 10;
  val JACK = 11;
  val QUEEN = 12;
  val KING = 13;
  
  def rank(card:Int) = card % 13
  def suit(card:Int) = card / 13
  
}

/** 
  This class implement a card instance 
*/
case class Card (number : Int, suite : Int) {
  /** 
    Returns true if this card is an ace 
  */
  def ace = number == 1
  
  /** 
    Returns true if this card has value of a 10 
  */
  def ten = number >= 10
  
  /** 
    Returns the "flat" value of the card 
  */
  def value = flat(number)
  
  /** 
    Flattens the value of card 
  */
  def flat(value : Int) = if(value <= 10) value else 10  
  
  /** 
    Returns the pretty value of the card 
  */
  override def toString : String = {
    var s : String = ""

    number match {
      case n : Int if n >= 2 && n <= 10 =>
        s = n + ""
        
      case Card.JACK =>
        s = "Jack"
          
      case Card.QUEEN =>
        s = "Queen"
          
      case Card.KING =>
        s = "King"
      
      case Card.ACE =>
        s = "Ace"
       
      case _ =>
        s = "???"
    }
    
    s += " of "
    
    suite match {
      case Card.SPADES =>
        s += "Spades"
         
      case Card.CLUBS =>
        s += "Clubs"
          
      case Card.HEARTS =>
        s += "Hearts"
          
      case Card.DIAMONDS =>
        s += "Diamonds"
      
      case _ =>
        s += "???"
    }
    s
  }
}