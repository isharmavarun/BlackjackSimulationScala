package baseclasses

import scala.collection.mutable.ArrayBuffer

class Hand {
	//An array to hold all cards in a dealer/player hand.
	var cards = new ArrayBuffer[Card]()

	/** 
	  Adds a card to the hand of dealer/player. 
  	*/
	def addCard(newCard: Card) {
		if (cards.size < 22) {
			cards += newCard
		}
	}

	/** 
	  Gets the value of hand held by dealer/player 
  	*/
	def value : Int = {
		// Get the count for aces as soft
      	var count : Int = softValue
      
      	// Count aces
      	val aceCount = cards.count(card => card.ace)
      
      	// Use ace == 11 if it favors the player
      	if(hardSoft(aceCount) && count >= 7 && (count+10) <= 21)
          	count += 10
      	else if(hardSoft(aceCount) && count+10 <= 21)
          	count += 10
      
      	count
	}

	/** 
	  Checks if the dealer/player hand is hard or soft.
  	*/
	def hardSoft(aceCount:Int) : Boolean = aceCount == 1 || aceCount > 1

	/** 
	  Gets the soft value of the hand.
  	*/
	def softValue : Int = cards.foldLeft(0)((xs, x) => xs + x.value)
}