import java.util.Random;
import java.util.Scanner;

/*
 * Java implementation of Wordle from the New York Times.
 * 
 * @author Michael Schwartz
 * @date 12/28/23
 */
public class Wordle {
    private static String target;
    private static final String GREEN_TEXT = "\u001B[32m";
    private static final String YELLOW_TEXT = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    // Sourced from https://byjus.com/english/5-letter-words/
    private static final String[] words = {
        "About", "Alert", "Argue", "Beach",
        "Above", "Alike", "Arise", "Began",
        "Abuse", "Alive", "Array", "Begin",
        "Actor", "Allow", "Aside", "Begun",
        "Acute", "Alone", "Asset", "Being",
        "Admit", "Along", "Audio", "Below",
        "Adopt", "Alter", "Audit", "Bench",
        "Adult", "Among", "Avoid", "Billy",
        "After", "Anger", "Award", "Birth",
        "Again", "Angle", "Aware", "Black",
        "Agent", "Angry", "Badly", "Blame",
        "Agree", "Apart", "Baker", "Blind",
        "Ahead", "Apple", "Bases", "Block",
        "Alarm", "Apply", "Basic", "Blood",
        "Album", "Arena", "Basis", "Board",
        "Boost", "Buyer", "China", "Cover",
        "Booth", "Cable", "Chose", "Craft",
        "Bound", "Calif", "Civil", "Crash",
        "Brain", "Carry", "Claim", "Cream",
        "Brand", "Catch", "Class", "Crime",
        "Bread", "Cause", "Clean", "Cross",
        "Break", "Chain", "Clear", "Crowd",
        "Breed", "Chair", "Click", "Crown",
        "Brief", "Chart", "Clock", "Curve",
        "Bring", "Chase", "Close", "Cycle",
        "Broad", "Cheap", "Coach", "Daily",
        "Broke", "Check", "Coast", "Dance",
        "Brown", "Chest", "Could", "Dated",
        "Build", "Chief", "Count", "Dealt",
        "Built", "Child", "Court", "Death",
        "Debut", "Entry", "Forth", "Group",
        "Delay", "Equal", "Forty", "Grown",
        "Depth", "Error", "Forum", "Guard",
        "Doing", "Event", "Found", "Guess",
        "Doubt", "Every", "Frame", "Guest",
        "Dozen", "Exact", "Frank", "Guide",
        "Draft", "Exist", "Fraud", "Happy",
        "Drama", "Extra", "Fresh", "Harry",
        "Drawn", "Faith", "Front", "Heart",
        "Dream", "False", "Fruit", "Heavy",
        "Dress", "Fault", "Fully", "Hence",
        "Drill", "Fibre", "Funny", "Night",
        "Drink", "Field", "Giant", "Horse",
        "Drive", "Fifth", "Given", "Hotel",
        "Drove", "Fifty", "Glass", "House",
        "Dying", "Fight", "Globe", "Human",
        "Eager", "Final", "Going", "Ideal",
        "Early", "First", "Grace", "Image",
        "Earth", "Fixed", "Grade", "Index",
        "Eight", "Flash", "Grand", "Inner",
        "Elite", "Fleet", "Grant", "Input",
        "Empty", "Floor", "Grass", "Issue",
        "Enemy", "Fluid", "Great", "Irony",
        "Enjoy", "Focus", "Green", "Juice",
        "Enter", "Force", "Gross", "Joint",
        "Judge", "Metal", "Media", "Newly",
        "Known", "Local", "Might", "Noise",
        "Label", "Logic", "Minor", "North",
        "Large", "Loose", "Minus", "Noted",
        "Laser", "Lower", "Mixed", "Novel",
        "Later", "Lucky", "Model", "Nurse",
        "Laugh", "Lunch", "Money", "Occur",
        "Layer", "Lying", "Month", "Ocean",
        "Learn", "Magic", "Moral", "Offer",
        "Lease", "Major", "Motor", "Often",
        "Least", "Maker", "Mount", "Order",
        "Leave", "March", "Mouse", "Other",
        "Legal", "Music", "Mouth", "Ought",
        "Level", "Match", "Movie", "Paint",
        "Light", "Mayor", "Needs", "Paper",
        "Limit", "Meant", "Never", "Party",
        "Peace", "Power", "Radio", "Round",
        "Panel", "Press", "Raise", "Route",
        "Phase", "Price", "Range", "Royal",
        "Phone", "Pride", "Rapid", "Rural",
        "Photo", "Prime", "Ratio", "Scale",
        "Piece", "Print", "Reach", "Scene",
        "Pilot", "Prior", "Ready", "Scope",
        "Pitch", "Prize", "Refer", "Score",
        "Place", "Proof", "Right", "Sense",
        "Plain", "Proud", "Rival", "Serve",
        "Plane", "Prove", "River", "Seven",
        "Plant", "Queen", "Quick", "Shall",
        "Plate", "Sixth", "Stand", "Shape",
        "Point", "Quiet", "Roman", "Share",
        "Pound", "Quite", "Rough", "Sharp",
        "Sheet", "Spare", "Style", "Times",
        "Shelf", "Speak", "Sugar", "Tired",
        "Shell", "Speed", "Suite", "Title",
        "Shift", "Spend", "Super", "Today",
        "Shirt", "Spent", "Sweet", "Topic",
        "Shock", "Split", "Table", "Total",
        "Shoot", "Spoke", "Taken", "Touch",
        "Short", "Sport", "Taste", "Tough",
        "Shown", "Staff", "Taxes", "Tower",
        "Sight", "Stage", "Teach", "Track",
        "Since", "Stake", "Teeth", "Trade",
        "Sixty", "Start", "Texas", "Treat",
        "Sized", "State", "Thank", "Trend",
        "Skill", "Steam", "Theft", "Trial",
        "Sleep", "Steel", "Their", "Tried",
        "Slide", "Stick", "Theme", "Tries",
        "Small", "Still", "There", "Truck",
        "Smart", "Stock", "These", "Truly",
        "Smile", "Stone", "Thick", "Trust",
        "Smith", "Stood", "Thing", "Truth",
        "Smoke", "Store", "Think", "Twice",
        "Solid", "Storm", "Third", "Under",
        "Solve", "Story", "Those", "Undue",
        "Sorry", "Strip", "Three", "Union",
        "Sound", "Stuck", "Threw", "Unity",
        "South", "Study", "Throw", "Until",
        "Space", "Stuff", "Tight", "Upper",
        "Upset", "Whole", "Waste", "Wound",
        "Urban", "Whose", "Watch", "Write",
        "Usage", "Woman", "Water", "Wrong",
        "Usual", "Train", "Wheel", "Wrote",
        "Valid", "World", "Where", "Yield",
        "Value", "Worry", "Which", "Young",
        "Video", "Worse", "While", "Youth",
        "Virus", "Worst", "White", "Worth",
        "Visit", "Would", "Vital", "Voice"
    };

    /*
     * main method for class Wordle
     */
    public void main(String[] args) {
        System.exit(driver());
    }


    /*
     * Generates random word from the words[] array as the target word for the game
     * 
     * @return String word
     */
    private static String pickWord() {
        Random rand = new Random();
        return words[rand.nextInt(words.length - 1)];
    }

    /*
     * Driver for the Wordle class. Runs the game and prompts the user for input and checks it against the target word.
     * Returns 1 to end the game when the word is guessed OR the limit on guesses is reached
     * 
     * @return int to end game
     */
    private static int driver() {
        target = pickWord().toLowerCase();
        System.out.println(target);
        int guesses = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Guess the five letter word: 5 guesses remain...");
        while(guesses <= 6) {
            guesses++;
            String guess = input.nextLine();

            // Checks if the input is a 5 letter word
            while(guess.length() != 5) {
                System.out.println("Please enter a 5 letter word!");
                guess = input.nextLine();
            }

            if(guess.equals(target)) {
                System.out.println("Congratulations! It took you " + guesses + " tries to guess the word.");
                return 1;
            } else {
                char[] c = guess.toCharArray();
                // Compares each letter in guess to the target word
                for(int i = 0; i < 5; i++) {
                    if(c[i] == target.charAt(i)) {
                        System.out.print(GREEN_TEXT + c[i] + RESET);
                    } else if(target.indexOf(c[i]) != -1) {
                        System.out.print(YELLOW_TEXT + c[i] + RESET);
                    } else {
                        System.out.print(c[i]);
                    }
                }
                System.out.print("\n\n");
            }
        }
        System.out.println("You have run out of guesses. The word was " + target + ".");
        return 1;
    }
}