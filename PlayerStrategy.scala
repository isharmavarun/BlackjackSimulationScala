package baseclasses

import scala.io.Source

/**
  Implements the player strategy. Decides to play hard, soft or pair strategy.
 */
trait PlayerStrategy extends Strategy {

	//Load the strategies from the files into variables.
	var hardStratArr = Source.fromFile("HardStrategy.txt").getLines.toArray
	var softStratArr = Source.fromFile("SoftStrategy.txt").getLines.toArray
	var pairStratArr = Source.fromFile("PairStrategy.txt").getLines.toArray

	//Construct arrays to store the strategy information
	var hardStrat = Array.ofDim[Char](13, 10)
	var softStrat = Array.ofDim[Char](8, 10)
	var pairStrat = Array.ofDim[Char](10, 10)

	//Read each line and create a character strategy array for the Hard strategy.
	for(i <- 0 to hardStratArr.size - 1) {
		var split = hardStratArr(i).split("\\|")
		for(j <- 2 to split.size) {
			hardStrat(i)(j - 2) = split(j - 1).charAt(0)
		}
	}

	//Read each line and create a character strategy array for the Soft strategy.
	for(i <- 0 to softStratArr.size - 1) {
		var split = softStratArr(i).split("\\|")
		for(j <- 2 to split.size) {
			softStrat(i)(j - 2) = split(j - 1).charAt(0)
		}
	}

	//Read each line and create a character strategy array for the Pair strategy.
	for(i <- 0 to pairStratArr.size - 1) {
		var split = pairStratArr(i).split("\\|")
		for(j <- 2 to split.size) {
			pairStrat(i)(j - 2) = split(j - 1).charAt(0)
		}
	}

	/**
	  Decides the next move for a player based on the player hand and the dealer top card.
	 */
	override def action(playerHand: Hand, splitAllowed: Boolean): Char = {
		
		//Get displayed card of the dealer.
		var dealerCard = Dealer.getTopCard()
		var dealerCardIndex = 0
		
		//Match the dealer card value to the column value for any strategy.
		dealerCard match {
			case n if n == 1 => dealerCardIndex = 9
			case n if n > 9 => dealerCardIndex = 8
			case n if n > 1 || n <= 9 => dealerCardIndex = dealerCard - 1
			case n if n <= 0 || n > 13 => println("Invalid dealer card value.") 
		}

		//Check if the player can play the pair strategy
		if(playerHand.cards.size == 2 && playerHand.cards(0).value == playerHand.cards(1).value && splitAllowed) {
			//Get the value corresponding to the pair strategy table
			playerHand.cards(0).value match {
				case 1 => return pairStrat(9)(dealerCardIndex)								//If the value is ace
				case n if n > 9 => return pairStrat(8)(dealerCardIndex)						//If the value is a face card
				case n if n > 1 && n <= 9 => return pairStrat(playerHand.cards(0).value - 2)(dealerCardIndex) //Everything else
			}
		} else {
			//Get the ace count of the hand
			val aceCount = playerHand.cards.count(card => card.ace)			

			//Check if the hand is hard soft and decide to use hard or soft strategy accordingly.
			if(!playerHand.hardSoft(aceCount) || playerHand.value > 10) {//hard table can handle input starting from 5
				if(playerHand.value >= 17) {
					return hardStrat(12)(dealerCardIndex)				 //If the hand value is 17 or up
				} else {
					if(playerHand.value - 5 < 0 ) {						 //If the hand value is less than 5 then Hit
						return 'H'
					}
					return hardStrat(playerHand.value - 5)(dealerCardIndex) //Get the hard table strategy for the hand value
				}
			} else {
				return softStrat(playerHand.value - 3)(dealerCardIndex)		//Get the soft table strategy for the hand value
			}
		}
		
	}

	/**
	 Betting method for a player.
	 */
	def bet(amount: Int) {

	}
}