package baseclasses

/**
  Object to simulate the Blackjack game.
 */
object BlackjackSimulation {

	def main(args: Array[String]): Unit = {
		println()
		var continue = true												//Variable to check if the player has enough money to bet in the game.
		val shoe = new Shoe(5, 0.25)									//Create a shoe with 5 decks and 0.25 reshuffle percent.
		shoe.shuffle()

		val player1 = new Player(shoe)									//Simulate a player
		val dealer = new Dealer(shoe)									//Simulate a dealer

		val betAmt = 25.0
		for (i <- 0 to 10) {
			player1.clear()												//Reset the player
			dealer.clear()												//Reset the dealer

			if(player1.getBalance >= 25.0 && continue) {				//Check if player has enough money to bet
				player1.betMoney(betAmt)								//Bet 25 units. Value hardcoded.

				player1.play(true)										//Initiatialize player
				dealer.play()											//Initialize dealer

				println("+++++ Player1 cards +++++")
				player1.playerHand.cards.foreach(println)				//Display player original hand cards
				println("+++++++++++++++++++++++++")
				println()

				if(player1.isSplit) {
					println("***** Player1 Split hand *****")
					player1.playerSplitHand.cards.foreach(println)		//Display player splitted hand cards if any
					println("******************************")
					println()
				}

				println("------ Dealer cards ------")
				dealer.dealerHand.cards.foreach(println)				//Display dealer hand cards
				println("--------------------------")
				println()

				//Display player original hand results
				if (dealer.getHandValue < player1.getHandValue && player1.getHandValue <= 21) {
					player1.playerWon()
					println("======> Player Strategy: " + player1.getStrategyUsed)
					println("======> Dealer Strategy: " + dealer.getStrategyUsed)
					println()
					println("====== Player1 won ======")
					println()
				}else if (dealer.getHandValue > player1.getHandValue  && dealer.getHandValue <= 21) {
					player1.playerLost()
					println("======> Player Strategy: " + player1.getStrategyUsed)
					println("======> Dealer Strategy: " + dealer.getStrategyUsed)
					println()
					println("====== Player1 lost ======")
					println()
				}else if (player1.isBusted){
					player1.playerLost()
					println("======> Player Strategy: " + player1.getStrategyUsed)
					println("======> Dealer Strategy: " + dealer.getStrategyUsed)
					println()
					println("====== Player1 busted ======")
					println()
				}else if (dealer.isBusted) {
					player1.playerWon()
					println("======> Player Strategy: " + player1.getStrategyUsed)
					println("======> Dealer Strategy: " + dealer.getStrategyUsed)
					println()
					println("====== Dealer busted ======")
					println()
				}else if(player1.getHandValue == dealer.getHandValue) {
					player1.push()
					println("======> Player Strategy: " + player1.getStrategyUsed)
					println("======> Dealer Strategy: " + dealer.getStrategyUsed)
					println()
					println("====== Push ======")
					println()
				}else {
					println("Something went wrong.")
				}

				//Check if player bought insurance and the dealer has a blackjack with only 2 cards in his hand.
				if(player1.isInsured && dealer.getHandValue == 21 && dealer.dealerHand.cards.size == 2) {
					player1.money += betAmt/2
					println("%%%%%% Player won insurance. %%%%%%")
					println()
				}
				else if(player1.isInsured && dealer.getHandValue != 21) {
					player1.money -= betAmt/2
					println("%%%%%% Player lost insurance. %%%%%%")
					println()
				}

				//Display result for player splitted hand results if any.
				if(player1.isSplit) {
					if (dealer.getHandValue < player1.getSplitHandValue && player1.getSplitHandValue <= 21) {
						player1.playerWon()
						println("======> Player Strategy: " + player1.getSplitStrategyUsed)
						println("======> Dealer Strategy: " + dealer.getStrategyUsed)
						println()
						println("====== Player1 split hand won ======")
						println()
					}else if (dealer.getHandValue > player1.getSplitHandValue  && dealer.getHandValue <= 21) {
						player1.playerLost()
						println("======> Player Strategy: " + player1.getSplitStrategyUsed)
						println("======> Dealer Strategy: " + dealer.getStrategyUsed)
						println()
						println("====== Player1 split hand lost ======")
						println()
					}else if (player1.isSplitBusted){
						player1.playerLost()
						println("======> Player Strategy: " + player1.getSplitStrategyUsed)
						println("======> Dealer Strategy: " + dealer.getStrategyUsed)
						println()
						println("====== Player1 split hand busted ======")
						println()
					}else if (dealer.isBusted) {
						player1.playerWon()
						println("======> Player Strategy: " + player1.getSplitStrategyUsed)
						println("======> Dealer Strategy: " + dealer.getStrategyUsed)
						println()
						println("====== Dealer busted - split hand ======")
						println()
					}else if(player1.getSplitHandValue == dealer.getHandValue) {
						player1.push()
						println("======> Player Strategy: " + player1.getSplitStrategyUsed)
						println("======> Dealer Strategy: " + dealer.getStrategyUsed)
						println()
						println("====== Push - split hand ======")
						println()
					}else {
						println("Something went wrong.")
					}
				}

			}else {
				continue = false											//Player does not have any enough money
			}
		}

		if(!continue)
			println("############# Player ran out of money #############")

		val balanceInfo = player1.getPlayerBalanceInfo
		println("Player minimum balance: " + balanceInfo._1)
		println("Player maximum balance: " + balanceInfo._2)
		println("Player final balance: " + balanceInfo._3)
	}

}