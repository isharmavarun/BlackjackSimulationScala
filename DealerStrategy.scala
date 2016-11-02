package baseclasses

/**
  Implements the dealer strategy. Decides to hit or stand.
 */
trait DealerStrategy extends Strategy {
	
	/**
	  Decides the next move for the dealer based on the hand value.
	 */
	override def action(dealerHand: Hand, splitAllowed: Boolean): Char = {
		
		if(dealerHand.value < 17) {
			'H'
		} else
			'S'
	}

	/**
	 Betting method for a player.
	 */
	def bet(amount: Int) {
		
	}
}