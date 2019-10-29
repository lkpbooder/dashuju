package prepro;

public class CityPreprocess implements ICityExtraction {

	@Override
	public String[] extractCity(String state) {
		// TODO Auto-generated method stub
		String result[] = { "", "" };
		int firstStartBracket = state.indexOf("【");
		int firstEndBracket = state.indexOf("】");
		if (firstStartBracket == -1 || firstEndBracket == -1)
			return null;

		result[0] = state.substring(firstStartBracket + 1, firstEndBracket);
		// System.out.println(state);
		int secondStartBracket = state.indexOf("【", firstStartBracket + 1);
		int secondEndBracket = state.indexOf("】", firstEndBracket + 1);
		if (secondStartBracket == -1 || secondEndBracket == -1)
			return result;
		result[1] = "("+state.substring(secondStartBracket + 1, secondEndBracket)+")";
		return result;
	}

}
