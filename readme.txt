================== Homework 3 ====================

Program Description:
	- The program simulates a game between a dealer and a single player.
	- The output will contain a sequence of games simulated between a player and a dealer. It shows who's hand was stronger and displays the results accordingly.
	- The program uses appropriate game strategies for the player and the dealer to decide the next move. It reads the strategies from the HardStrategy, SoftStrategy, and PairStrategy files present in the folder.
	- The player is capable of betting, hitting, stand, double down, split, and buy insurance in the simulation.
	- The number of games can be controlled in the BlackjackSimulation.scala file where the for loop range contains the amount of the games run. At present, 1000 games are played between the dealer and the player. The simulation stops until the player runs out of money or all the 1000 games are completed.
	- The simulated game bets a constant value of 25 units for a player. The value is hardcoded in the file and can be changed.
	- The player has a balance of 100 units initially before the game is started.
	- The output of the simulation contains, the player hands (original and splitted hand if any), strategy used by the player and the dealer, the game result and finally, towards the end, the minimum, maximum and the final balance the player had during the entire simulation.

House rules:
	- A hand can be splitted only once.
	- After a double down strategy, the player is allowed to add only one card to his hand.

Files:
	- Card.scala
	- Shoe.scala
	- Hand.scala
	- Player.scala
	- Dealer.scala
	- PlayerStrategy.scala
	- DealerStrategy.scala
	- BlackjackSimulation.scala
	- HardStrategy.txt
	- SoftStrategy.txt
	- PairStrategy.txt

How to compile:
	- Extract the zip file into a directory.
	- Go inside the blackjack folder to confirm the files are present.
	- Fire up terminal or command prompt depending on the OS, and navigate to the "blackjack" folder.
	- Run the command "sbt" on the terminal/prompt.
	- Enter the command "compile".

How to run:
	- Enter the command "run" from the sbt terminal to see the output.

Sample Output:
	+++++ Player1 cards +++++
	7 of Hearts
	Jack of Spades
	+++++++++++++++++++++++++

	------ Dealer cards ------
	5 of Spades
	King of Hearts
	2 of Hearts
	--------------------------

	======> Player Strategy: S
	======> Dealer Strategy: HS

	====== Push ======

	+++++ Player1 cards +++++
	Jack of Spades
	10 of Clubs
	+++++++++++++++++++++++++

	------ Dealer cards ------
	2 of Spades
	Queen of Diamonds
	Jack of Clubs
	--------------------------

	======> Player Strategy: S
	======> Dealer Strategy: H

	====== Dealer busted ======

	+++++ Player1 cards +++++
	4 of Clubs
	3 of Spades
	7 of Diamonds
	8 of Spades
	+++++++++++++++++++++++++

	------ Dealer cards ------
	3 of Diamonds
	Jack of Diamonds
	7 of Hearts
	--------------------------

	======> Player Strategy: HH
	======> Dealer Strategy: HS

	====== Player1 busted ======

	+++++ Player1 cards +++++
	Ace of Diamonds
	Queen of Hearts
	+++++++++++++++++++++++++

	------ Dealer cards ------
	2 of Diamonds
	8 of Hearts
	9 of Clubs
	--------------------------

	======> Player Strategy: S
	======> Dealer Strategy: HS

	====== Player1 won ======

	+++++ Player1 cards +++++
	Ace of Clubs
	5 of Spades
	4 of Spades
	+++++++++++++++++++++++++

	------ Dealer cards ------
	King of Clubs
	Ace of Hearts
	--------------------------

	======> Player Strategy: HS
	======> Dealer Strategy: S

	====== Player1 lost ======

	%%%%%% Player won insurance. %%%%%%

	+++++ Player1 cards +++++
	Queen of Diamonds
	4 of Diamonds
	7 of Spades
	+++++++++++++++++++++++++

	------ Dealer cards ------
	King of Hearts
	10 of Clubs
	--------------------------

	======> Player Strategy: HS
	======> Dealer Strategy: S

	====== Player1 won ======

	+++++ Player1 cards +++++
	8 of Spades
	Ace of Clubs
	+++++++++++++++++++++++++

	------ Dealer cards ------
	5 of Diamonds
	3 of Hearts
	5 of Hearts
	4 of Spades
	--------------------------

	======> Player Strategy: S
	======> Dealer Strategy: HHS

	====== Player1 won ======

	+++++ Player1 cards +++++
	8 of Hearts
	7 of Diamonds
	8 of Hearts
	+++++++++++++++++++++++++

	------ Dealer cards ------
	Queen of Clubs
	7 of Clubs
	--------------------------

	======> Player Strategy: H
	======> Dealer Strategy: S

	====== Player1 busted ======

	+++++ Player1 cards +++++
	5 of Hearts
	Ace of Hearts
	+++++++++++++++++++++++++

	------ Dealer cards ------
	4 of Spades
	3 of Hearts
	5 of Diamonds
	3 of Clubs
	4 of Diamonds
	--------------------------

	======> Player Strategy: S
	======> Dealer Strategy: HHHS

	====== Player1 lost ======

	+++++ Player1 cards +++++
	Jack of Hearts
	4 of Diamonds
	King of Clubs
	+++++++++++++++++++++++++

	------ Dealer cards ------
	5 of Diamonds
	9 of Clubs
	5 of Hearts
	--------------------------

	======> Player Strategy: H
	======> Dealer Strategy: HS

	====== Player1 busted ======

	+++++ Player1 cards +++++
	2 of Clubs
	6 of Spades
	Jack of Clubs
	+++++++++++++++++++++++++

	------ Dealer cards ------
	3 of Clubs
	Queen of Clubs
	4 of Hearts
	--------------------------

	======> Player Strategy: HS
	======> Dealer Strategy: HS

	====== Player1 won ======

	Player minimum balance: 75.0
	Player maximum balance: 275.0
	Player final balance: 200.0