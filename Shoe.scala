package baseclasses

import scala.util.Random

/**
 Class Shoe holding all the deck of cards. Contains helper methods to populate the deck, 
 shuffle the deck and deal a card from the deck.
 */
class Shoe(val numOfDecks: Int, val reshufflePercent: Double) {
	
	//The total number of cards in the deck
	var numOfCards = numOfDecks * 52

	//Deck to hold all the cards
	private var deck = new Array[Card](numOfCards)
	
	//Used to reshuffle the deck if it falls below the defined threshold
	private val shufflePercent = reshufflePercent

	private var currentCardIndex = 0

	populateShoe()

	/** 
	  Populates the shoe/deck. 
  	*/
	def populateShoe() {

		for (d <- 1 to numOfDecks)
			for (code <- Card.ACE to Card.KING)
				for (suite <- Card.CLUBS to Card.DIAMONDS) {
					deck(currentCardIndex) = Card(code, suite)
					currentCardIndex += 1
				}

		currentCardIndex = 0
	}	

	/** 
	  shuffles the shoe/deck
  	*/
	def shuffle() {	

		for (n <- Iterator.range(deck.length - 1, 0, -1)) {
	      val randomIndex = Random.nextInt(numOfCards - 1)
	      val temp = deck(randomIndex); 
	      deck(randomIndex) = deck(n); 
	      deck(n) = temp
	    }

	    currentCardIndex = 0
	}
	
	/** 
	  deals one card from the shoe/deck
  	*/
	def dealCard () : Card = {
		var deltCard = deck(currentCardIndex)
		currentCardIndex += 1

		var checkShufflePercent: Double = (numOfCards.toDouble - currentCardIndex.toDouble)/(numOfCards.toDouble)
		if(checkShufflePercent < shufflePercent)
			shuffle()
		
		return deltCard
	}
}