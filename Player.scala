package baseclasses

import scala.collection.mutable.ArrayBuffer

/** 
  Class which holds all the information about a Player during 
  the Blackjack simulation.
 */
class Player(shoe: Shoe) extends PlayerStrategy {
	
	var playerHand = new Hand 										//Player hand
	var playerSplitHand = new Hand 									//The hand if player splits the original hand

	var money = 100.0 												//Money held by a player. Hardcoded to 100
	var currentBet = 0.0 											//The betting amount in every play

	var isBusted = false 											//Flag to check if hand is busted ( > 21)
	var isSplit = false 											//Flag to check if the original hand is splitted
	var isSplitBusted = false										//Flag to check if the splitted hand is busted ( > 21)
	var isInsured = false											//Flag to check if the player bought insurance

	var strategyUsed: String = ""									//String to hold the strategy used by the player
	var strategyUsedSplit: String = ""								//String to hold the strategy used by the player
																	//for the splitted hand.	

	var playerHandBalance = new ArrayBuffer[Double]()				//An array to hold the money balance of a player
																	//during the game.

	//Add two cards initially to the player hand																	
	playerHand.addCard(shoe.dealCard())
	playerHand.addCard(shoe.dealCard())

	/**
	  Method to reset Player's hand.
     */
	def clear() {
		isBusted = false
		isSplit = false
		isSplitBusted = false
		isInsured = false
		currentBet = 0.0
		strategyUsed = ""
		strategyUsedSplit = ""

		playerHand = new Hand
		playerHand.addCard(shoe.dealCard())
		playerHand.addCard(shoe.dealCard())

		playerSplitHand = new Hand
	}

	/**
	  Recursive method which plays for a Player. Based on the strategy it
	  decides the next action to be taken for a player.
     */
	def play(splitAllowed: Boolean) {

		var act = action(playerHand, splitAllowed)					//The player strategy method which decides the next move.
		
		if(playerHand.cards.size == 2 && Dealer.hasAceTopCard)		//Buy insurance if dealer has an Ace as the top card.
			buyInsurance()

		//If the player splitted his hand then call the other recursive for the splitted hand
		if(isSplit)	
			playSplit(false)

		act match {
			case 'H' => playerHand.addCard(shoe.dealCard())			//Hit the hand
						strategyUsed += "H"
						if(playerHand.value > 21)
							isBusted = true
						else
							play(true)								//If not busted then decide the next move.
			case 'S' => strategyUsed += "S"							//Player decided to stand. Do nothing.
			case 'D' => doubleDown()								//Double down the bet. Add a card to the hand.
						strategyUsed += "D"
						playerHand.addCard(shoe.dealCard())
						if(playerHand.value > 21)
							isBusted = true
			case 'P' => strategyUsed += "P"							//Split the hand. Create a new Hand for the splitted hand.
						isSplit = split()
						play(false)									//Play the next move for the original hand.
			case  _  => println("Unexpected input.")
		}
	}

	def playSplit(splitAllowed: Boolean) {
		var act = action(playerSplitHand, splitAllowed)				//The player strategy method which decides the next move.

		act match {
			case 'H' => playerSplitHand.addCard(shoe.dealCard())	//Hit the splitted hand
						strategyUsedSplit += "H"
						if(playerSplitHand.value > 21)
							isSplitBusted = true
						else
							playSplit(false)
			case 'S' => strategyUsedSplit += "S"					//Player decided to do nothing.
			case 'D' => doubleDown()								//Double down the bet. Add a card to the hand.
						strategyUsedSplit += "D"
						playerSplitHand.addCard(shoe.dealCard())
						if(playerSplitHand.value > 21)
							isSplitBusted = true
			case  _  => println("Unexpected input.")
		}
	}

	/**
	  Bets the money for the current hand
	 */
	def betMoney(m : Double){
	   	if(m <= money){
		    currentBet = m
		    money = money - m
	  	}

	  	playerHandBalance += money 									//Stores the balance to keep track of player balance.
	}

	/**
	  Double down the bet.
	 */
  	def doubleDown() {
	    if (money >= 0.5 * currentBet ) {
       		money = money - 0.5 * currentBet
       		currentBet = 1.5 * currentBet
	    }
  	}

  	/**
	  Splits the player hand.
	 */
  	def split():Boolean = {
  		//Check if player has enough balance to split the hand.
  		if(money >= currentBet * 2 ) {
	  		playerSplitHand.addCard(playerHand.cards.drop(1)(0))
	  		playerHand.cards = playerHand.cards.dropRight(1)

	  		bet(currentBet.toInt)
	  		return true
  		} else {
  			//Not enough money to split
  			return false
  		}
  	}

  	/**
	  Buy insurance if dealer has an Ace top card.
	 */
  	def buyInsurance() {
	    if(money >= currentBet /2 ) {
      		money = money - currentBet /2
      		isInsured = true
      	}
  	}

  	/**
	  Reset the current bet if the player lost.
	 */
 	def playerLost(): Unit = {
	  	currentBet = 0.0
 	}

 	/**
	  Add the money back to the balance if player wins.
	 */
 	def playerWon(): Unit ={
	  	if(playerHand.value == 21) 
	  		money += currentBet * 5 
	  	else 
	  		money += currentBet * 2
	  	currentBet = 0.0
 	}

 	/**
	  Adds the bet back to the balance if the player draws with the dealer.
	 */
 	def push(): Unit = {
  		money += currentBet
  		currentBet = 0.0
 	}

 	/**
	  Get the original hand value.
	 */
 	def getHandValue(): Int = {
 		playerHand.value
 	}

 	/**
	  Get the splitted hand value.
	 */
 	def getSplitHandValue(): Int = {
 		playerSplitHand.value
 	}

 	/**
	  Get the current balance of the player.
	 */
 	def getBalance(): Double = money

 	/**
	  Get the original hand strategy used.
	 */
 	def getStrategyUsed(): String = strategyUsed

 	/**
	  Get the splitted hand strategy used.
	 */
 	def getSplitStrategyUsed(): String = strategyUsedSplit

 	/**
	  Get the min, max and final balance of the player.
	 */
 	def getPlayerBalanceInfo(): (Double, Double, Double) = 
 	(playerHandBalance.min, playerHandBalance.max, playerHandBalance(playerHandBalance.size - 1))

}