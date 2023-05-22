package rugby;

import java.util.ArrayList;
import java.util.Scanner;

public class MarketPlace extends GameEnviroment{
	public ArrayList<Athlete> playersForSale = new ArrayList<Athlete>();
	public ArrayList<Item> itemsForSale = new ArrayList<Item>();
	private GameEnviroment game;
	private Inventory club;
	
	public MarketPlace(GameEnviroment game, Inventory club) {
		this.game = game;
		this.club = club;

		for (int i = 0 ; i < 14 ; i++) {
			playersForSale.add(generatePlayer(game.difficulty));
		}
		
		for (int i = 0 ; i <= 2 ; i++) {
			itemsForSale.add(generateItem());
		}
	}
	
	public Athlete returnPlayer(int index) {
		return playersForSale.get(index);
	}
	
	public Item returnItem(int index) {
		return itemsForSale.get(index);
	}
	
	public void removeMarketPlayer(Athlete athlete) {
		playersForSale.remove(athlete);
	}
	
	public void removeMarketItem(Item item) {
		itemsForSale.remove(item);
	}
	
	public void updateMarket() {
		playersForSale.clear();
		itemsForSale.clear();
		for (int i = 0; i < 3; i++) {
			playersForSale.add(generatePlayer(game.difficulty));
			itemsForSale.add(generateItem());
			}
	}
	
	public String[] returnPlayerArray() { 
		ArrayList<String> playerArrayList = new ArrayList<>();
		
		for (Athlete athlete: this.playersForSale) {
			playerArrayList.add(String.format("%-40s", (athlete.getName() + " $" + athlete.getBuyPrice() + " Pos: " + athlete.getPosition() + " Rating: " + athlete.getRating() )));
		}
		
		String[] playerArray = playerArrayList.toArray(new String[0]);
		return playerArray;
	}
	
	public String[] returnItemArray() {
		ArrayList<String> itemArrayList = new ArrayList<>();
		
		for (Item item: this.itemsForSale) {
			itemArrayList.add(String.format("%-60s", item.toString()));
		}
		
		String[] itemArray = itemArrayList.toArray(new String[0]);
		return itemArray;
	}
	
	public String buyAthlete(int index) {
		String result = "";
		Athlete athlete = playersForSale.get(index);
		if (athlete.buyPrice <= game.getMoney() && club.getPlayers().size() < 5) {
			removeMarketPlayer(athlete);
			club.addPlayer(athlete);
			game.minusMoney(athlete.buyPrice);
			result = "Bought";
		}
		else if (athlete.buyPrice > game.getMoney()){
			result = "You Dont Have Enough Money";
		}
		else {
			result = "Your Club Is Full";
		}
		return result;
	}
	
	public String buyItem(int index) {
		String result = "";
		Item item = itemsForSale.get(index);
		if (item.getPrice() <= game.getMoney() && club.getItems().size() < 5) {
			removeMarketItem(item);
			club.addItem(item);
			game.minusMoney(item.getPrice());
			result = "Bought";
		}
		else if (item.getPrice() > game.getMoney()) {
			result = "You Dont Have Enough Money";
		}
		else {
			result = "Your Club Is Full";
		}
		return result;
	}
	
//	public void buyObject(Athlete athlete) {
//		if (game.moneyTransfer(-100)) {
//			System.out.println("Success");
//			club.addPlayer(athlete);
//		} else {
//			System.out.println("Fail");
//		}
//	}
	
//	public ArrayList<String> getAllItemsNames(){
//		ArrayList<String> itemNames = new ArrayList<String>();
//		for (Item item : this.itemsForSale) {
//			itemNames.add(item.item);
//		}
//		return itemNames;
//	}
	
//	public void sellObject(Athlete athlete) {
//		game.moneyTransfer(athlete.sellPrice);
//		club.removePlayer(athlete);
//		System.out.printf("Sold %s for %s", athlete.name, athlete.sellPrice);
//	}
	
//	public void listBuyables() {
//		System.out.println("Athletes Currently Available For Purchase:");
//		System.out.printf("%-4s%-10s%-10s%-10s%-10s%35s%n"," ", "Name", "Price", "Wage", "Rating", "Stats");
//		int i = 0;
//		for (Athlete athlete : this.playersForSale) {
//			System.out.printf("%-2s: %-10s$%-10s$%-10s%-10s%s%n", i, athlete.name, athlete.buyPrice, athlete.wage, athlete.rarity, athlete.stats);
//			i += 1;
//		}
//		
//		System.out.println();
//		System.out.println("Items Currently Available For Purchase:");
//		System.out.printf("%-4s%-10s%-10s%35s%n"," ", "Item", "Price", "Boosts");
//		
//		for (Item item : this.itemsForSale) {
//			System.out.printf("%-2s: %-10s$%-10s%s%n", i, item.item, item.price, item.stats);
//			i += 1;
//		}
//		
//		selectBuyables();
//	}
		
		
//	public void selectBuyables() {
//		try {
//			Scanner input = new Scanner(System.in);
//			System.out.println("Select An Object To Purchase, or press -1 to return: ");
//			int athlete = input.nextInt();
//			if (athlete == -1) {
//				return;
//			} else {
//				buyObject(playersForSale.get(athlete));
//			}
//			
//		}
//		
//		catch(Exception  e) {
//			System.out.println("Please Select A Valid Input");
//			selectBuyables();
//			
//		}
//	}

		
	
	
	public static void main(String[] args) {
	}

}
