// Name: Jiazhi Li
// USC NetID: jiazhil 
// USC ID : 5715388761
// CS 455 PA1
// Spring 2019

public class CoinTossSimulatorTester {
	public static void main(String[] args) {
		CoinTossSimulator test = new CoinTossSimulator();
		System.out.println("After constructor:");
		System.out.println("Number of trials [exp:0]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " + check(test.getNumTrials(), test.getTwoHeads(), test.getTwoTails(), test.getHeadTails()));
		System.out.println();
		
		test.run(1);
		System.out.println("After run(1):");
		System.out.println("Number of trials [exp:1]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " + check(test.getNumTrials(), test.getTwoHeads(), test.getTwoTails(), test.getHeadTails()));
		System.out.println();
		
		test.run(10);
		System.out.println("After run(10):");
		System.out.println("Number of trials [exp:11]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " + check(test.getNumTrials(), test.getTwoHeads(), test.getTwoTails(), test.getHeadTails()));
		System.out.println();
		
		test.run(100);
		System.out.println("After run(100):");
		System.out.println("Number of trials [exp:111]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " + check(test.getNumTrials(), test.getTwoHeads(), test.getTwoTails(), test.getHeadTails()));
		System.out.println();
	
		test.reset();
		System.out.println("After reset:");
		System.out.println("Number of trials [exp:0]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " + check(test.getNumTrials(), test.getTwoHeads(), test.getTwoTails(), test.getHeadTails()));
		System.out.println();
		
		test.run(11);
		System.out.println("After run(11):");
		System.out.println("Number of trials [exp:11]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " + check(test.getNumTrials(), test.getTwoHeads(), test.getTwoTails(), test.getHeadTails()));
		System.out.println();
		
		test.reset();
		test.run(1000);
		System.out.println("After run(1000):");
		System.out.println("Number of trials [exp:1000]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " + check(test.getNumTrials(), test.getTwoHeads(), test.getTwoTails(), test.getHeadTails()));
		System.out.println();
	}	
	
	private static String check(int NumTrials, int NumTwoHeads, int NumTwoTails, int NumHeadTails) {
		if(NumTrials == NumTwoHeads + NumTwoTails + NumHeadTails) {
			return "True";
		}else {return "False";}
	}
}
