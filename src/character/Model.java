/*
 * 
 * This class is the basis for every character that is created in the game.
 * 
 * Author: Josh Blitz
 * 
 */

package character;

public class Model implements IActions
{
	//constants
	//private static final int SECONDARY_BASE = 3 + Const.rollDice(3, 2, -1);
	
	//class
	private String charClass;
	
	//base stats
	private int strength,dexterity,twitch,constitution,intelligence,wisdom,commonSense,spirituality,charisma,luck,
			mHit, mMystic, mSkill, mPrayer, mBard;
	
	//current stats
	private int cStrength,cDexterity,cTwitch,cConstitution,cIntelligence,cWisdom,cCommonSense,cSpirituality,cCharisma,cLuck;
	private int cHit, cMystic, cSkill, cPrayer, cBard;
	//other attributes
	private int age, status, level, xp, rank, gold;
	//private int magicResist, commerce, rapport, recovery;
	
	private String name, race, gender, alignment, profession; 
	
	//armor class b=base, c=current
	private int bArmorClass, cArmorClass;
	
	//number attacks per round
	private int bNAT, cNAT; 
	
	//resurrection modifier
	private double resModifier;
	
	public Model(int strength,int dexterity,int twitch,int constitution,int intelligence,int wisdom,int commonSense,int spirituality,
			int charisma,int luck,int mHit,int mMystic,int mSkill,int mPrayer,int mBard, int bArmorClass, int bNAT, double resModifier)
	{
		super();
		level=1;
		status = 0;
	}
	@Override
	public void useWeapon() {
		// TODO Auto-generated method stub
		
	}

	public Model(String charClass, int strength, int dexterity, int twitch,
			int constitution, int intelligence, int wisdom, int commonSense,
			int spirituality, int charisma, int luck, int mHit, int mMystic,
			int mSkill, int mPrayer, int mBard, int age, int rank, int gold,
			String name, String race, String gender, String alignment,
			String profession, int bArmorClass, int cArmorClass, int bNAT,
			int cNAT, double resModifier) {
		super();
		this.charClass = charClass;
		this.strength = strength;
		this.dexterity = dexterity;
		this.twitch = twitch;
		this.constitution = constitution;
		this.intelligence = intelligence;
		this.wisdom = wisdom;
		this.commonSense = commonSense;
		this.spirituality = spirituality;
		this.charisma = charisma;
		this.luck = luck;
		this.mHit = mHit;
		this.mMystic = mMystic;
		this.mSkill = mSkill;
		this.mPrayer = mPrayer;
		this.mBard = mBard;
		this.age = age;
		this.rank = rank;
		this.gold = gold;
		this.name = name;
		this.race = race;
		this.gender = gender;
		this.alignment = alignment;
		this.profession = profession;
		this.bArmorClass = bArmorClass;
		this.cArmorClass = cArmorClass;
		this.bNAT = bNAT;
		this.cNAT = cNAT;
		this.resModifier = resModifier;
		level = 1;
		status = 0;
		resetCurrentStats();
	}
	// sets the current stats back to their max/base 
	private void resetCurrentStats() {
		// TODO Auto-generated method stub
		cStrength = strength; cDexterity = dexterity; cTwitch = twitch; cConstitution = constitution; cIntelligence = intelligence;
		cWisdom = wisdom; cCommonSense = commonSense; cSpirituality = spirituality; cCharisma = charisma; cLuck = luck; cHit = mHit;
		cMystic = mMystic; cSkill = mSkill; cPrayer = mPrayer; cBard = mBard; cArmorClass = bArmorClass;
	}
	@Override
	public void useMagic() {
		// TODO Auto-generated method stub
		
	}
	
	public String getCharClass() {
		return charClass;
	}

	public void setCharClass(String charClass) {
		this.charClass = charClass;
	}

	public int getSt() {
		return strength;
	}

	public void modSt(int mod) {
		this.strength += mod;
	}

	public int getDx() {
		return dexterity;
	}

	public void modDexterity(int mod) {
		this.dexterity += mod;
	}

	public int getTw() {
		return twitch;
	}

	public void modTw(int mod) {
		this.twitch += mod;
	}

	public int getCn() {
		return constitution;
	}

	public void modCn(int constitution) {
		this.constitution = constitution;
	}

	public int getIn() {
		return intelligence;
	}

	public void modIn(int mod) {
		this.intelligence += mod;
	}

	public int getWi() {
		return wisdom;
	}

	public void modWi(int mod) {
		this.wisdom += mod;
	}

	public int getCs() {
		return commonSense;
	}

	public void modCs(int mod) {
		this.commonSense += mod;
	}

	public int getSp() {
		return spirituality;
	}

	public void modSp(int mod) {
		this.spirituality += mod;
	}

	public int getCh() {
		return charisma;
	}

	public void modCh(int mod) {
		this.charisma += mod;
	}

	public int getLk() {
		return luck;
	}

	public void modLk(int mod) {
		this.luck += mod;
	}

	public int getcSt() {
		return cStrength;
	}

	public void modcSt(int mod) {
		this.cStrength += mod;
	}

	public int getcDx() {
		return cDexterity;
	}

	public void modcDx(int mod) {
		this.cDexterity += mod;
	}

	public int getcTw() {
		return cTwitch;
	}

	public void modcTw(int mod) {
		this.cTwitch += mod;
	}

	public int getcCn() {
		return cConstitution;
	}

	public void modcCn(int mod) {
		this.cConstitution += mod;
	}

	public int getcIn() {
		return cIntelligence;
	}

	public void modcIn(int mod) {
		this.cIntelligence += mod;
	}

	public int getcWi() {
		return cWisdom;
	}

	public void modcWi(int mod) {
		this.cWisdom += mod;
	}

	public int getcCs() {
		return cCommonSense;
	}

	public void modcCs(int mod) {
		this.cCommonSense += mod;
	}

	public int getcSp() {
		return cSpirituality;
	}

	public void modcSp(int mod) {
		this.cSpirituality += mod;
	}

	public int getcCh() {
		return cCharisma;
	}

	public void modcCh(int mod) {
		this.cCharisma += mod;
	}

	public int getcLk() {
		return cLuck;
	}

	public void modcLk(int mod) {
		this.cLuck += mod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public int getAge() {
		return age;
	}

	public void modAge(int mod) {
		this.age += mod;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLvl() {
		return level;
	}

	public void setLevel(int mod) {
		this.level += mod;
	}

	public int getXp() {
		return xp;
	}

	public void modXp(int mod) {
		this.xp += mod;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getGold() {
		return gold;
	}

	public void modGold(int mod) {
		this.gold += mod;
	}

	public int getcHit() {
		return cHit;
	}

	public void modcHit(int mod) {
		this.cHit += mod;
	}

	public int getmHit() {
		return mHit;
	}

	public void modmHit(int mod) {
		this.mHit += mod;
	}

	public int getcMystic() {
		return cMystic;
	}

	public void modcMystic(int mod) {
		this.cMystic += mod;
	}

	public int getmMystic() {
		return mMystic;
	}

	public void modmMystic(int mod) {
		this.mMystic += mod;
	}

	public int getcSkill() {
		return cSkill;
	}

	public void modcSkill(int mod) {
		this.cSkill += mod;
	}

	public int getmSkill() {
		return mSkill;
	}

	public void modmSkill(int mod) {
		this.mSkill += mod;
	}

	public int getcPrayer() {
		return cPrayer;
	}

	public void modcPrayer(int mod) {
		this.cPrayer += mod;
	}

	public int getmPrayer() {
		return mPrayer;
	}

	public void modmPrayer(int mod) {
		this.mPrayer += mod;
	}

	public int getcBard() {
		return cBard;
	}

	public void modcBard(int mod) {
		this.cBard += mod;
	}

	public int getmBard() {
		return mBard;
	}

	public void modmBard(int mod) {
		this.mBard += mod;
	}

	public int getbAC() {
		return bArmorClass;
	}

	public void modbAC(int mod) {
		this.bArmorClass += mod;
	}

	public int getcAC() {
		return cArmorClass;
	}

	public void modcAC(int mod) {
		this.cArmorClass += mod;
	}

	public int getbNAT() {
		return bNAT;
	}

	public void modbNAT(int mod) {
		this.bNAT += mod;
	}

	public int getcNAT() {
		return cNAT;
	}

	public void modcNAT(int mod) {
		this.cNAT += mod;
	}

	public double getResMod() {
		return resModifier;
	}

	public void modResMod(double mod) {
		this.resModifier += mod;
	}

}
