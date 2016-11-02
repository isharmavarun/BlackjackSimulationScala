package baseclasses

/** 
  Class which holds all the information about a Dealer during 
  the Blackjack simulation.
 */
class Dealer(shoe: Shoe) extends DealerStrategy{
	
	var dealerHand = new Hand

	var dealerStand = false
	var isBusted = false

	var strategyUsed: String = ""

	//Add two cards initially to the dealer hand
	dealerHand.addCard(shoe.dealCard())
	dealerHand.addCard(shoe.dealCard())
	Dealer.setTopCard(dealerHand.cards(1).value)							//Set the top card of the dealer for the players to see.

	if (dealerHand.cards(1).value == 1) 
		Dealer.hasAceTopCard = true			//This flag is for the player to buy insurance or not.
	else
		Dealer.hasAceTopCard = false
	/**
	  Method to reset Dealer's hand.
     */
	def clear() {
		strategyUsed = ""
		isBusted = false
		dealerStand = false

		dealerHand = new Hand
		dealerHand.addCard(shoe.dealCard())
		dealerHand.addCard(shoe.dealCard())
		if (dealerHand.cards(1).value == 1) 
			Dealer.hasAceTopCard = true			//This flag is for the player to buy insurance or not.
		else
			Dealer.hasAceTopCard = false
		Dealer.setTopCard(dealerHand.cards(1).value)
	}

	/**
	  Recursive method which plays for a Dealer. Based on the strategy it
	  decides the next action to be taken for a dealer.
     */
	def play() {

		var act = action(dealerHand, false)

		act match {
			case 'H' => strategyUsed += "H"									//Hit the dealer hand.
						dealerHand.addCard(shoe.dealCard())
						if(dealerHand.value > 21)
							isBusted = true
						else
							play()
			case 'S' => strategyUsed += "S"									//Dealer stand.
						dealerStand = true
			case  _  => println("Unexpected input.")
		}
	}

	/**
	  Get the dealer hand value.
	 */
	def getHandValue(): Int = {
 		dealerHand.value
 	}

 	/**
	  Get the dealer strategy used.
	 */
 	def getStrategyUsed(): String = strategyUsed

}

/**
  A companion object for the Dealer class which provides utility functions
  for accessing dealer information. For instance, to view the dealer top card
  and get the dealer hand value towards to check the value.
 */
object Dealer {

	var topCard = 0
	var hasAceTopCard = false

	/**
	  Sets the top(visible) card for the Dealer
	 */
	def setTopCard(card: Int){
		topCard = card
	}

	/**
	  Gets the top(visible) card for the Dealer
	 */
	def getTopCard(): Int = {
		return topCard
	}
}