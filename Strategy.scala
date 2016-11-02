package baseclasses

/**
  Abstract class to store all the methods to be used for 
  the strategy to be used by the player or the dealer.
 */
abstract class Strategy {

	/**
	 Action method to decide the strategy.
	 */
	def action(playerHand: Hand, splitAllowed: Boolean): Char = {
		return '0'
	}

	/**
	 Betting method for a player.
	 */
	def bet(amount: Int)
}