/*
 * generated by Xtext 2.10.0
 */
package dsl.xtext.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class DslGrammarAccess extends AbstractGrammarElementFinder {
	
	public class DSLElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "dsl.xtext.Dsl.DSL");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cSyntaxKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cSyntaxesAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cSyntaxesSyntaxParserRuleCall_2_0 = (RuleCall)cSyntaxesAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cCommaKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cSyntaxesAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cSyntaxesSyntaxParserRuleCall_3_1_0 = (RuleCall)cSyntaxesAssignment_3_1.eContents().get(0);
		private final Keyword cBehaviorKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Keyword cEqualsSignKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Assignment cBehavioursAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cBehavioursBehaviorParserRuleCall_6_0 = (RuleCall)cBehavioursAssignment_6.eContents().get(0);
		private final Group cGroup_7 = (Group)cGroup.eContents().get(7);
		private final Keyword cCommaKeyword_7_0 = (Keyword)cGroup_7.eContents().get(0);
		private final Assignment cBehavioursAssignment_7_1 = (Assignment)cGroup_7.eContents().get(1);
		private final RuleCall cBehavioursBehaviorParserRuleCall_7_1_0 = (RuleCall)cBehavioursAssignment_7_1.eContents().get(0);
		
		//DSL:
		//	'syntax' '=' syntaxes+=Syntax (',' syntaxes+=Syntax)*
		//	'behavior' '=' behaviours+=Behavior (',' behaviours+=Behavior)*;
		@Override public ParserRule getRule() { return rule; }
		
		//'syntax' '=' syntaxes+=Syntax (',' syntaxes+=Syntax)* 'behavior' '=' behaviours+=Behavior (',' behaviours+=Behavior)*
		public Group getGroup() { return cGroup; }
		
		//'syntax'
		public Keyword getSyntaxKeyword_0() { return cSyntaxKeyword_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_1() { return cEqualsSignKeyword_1; }
		
		//syntaxes+=Syntax
		public Assignment getSyntaxesAssignment_2() { return cSyntaxesAssignment_2; }
		
		//Syntax
		public RuleCall getSyntaxesSyntaxParserRuleCall_2_0() { return cSyntaxesSyntaxParserRuleCall_2_0; }
		
		//(',' syntaxes+=Syntax)*
		public Group getGroup_3() { return cGroup_3; }
		
		//','
		public Keyword getCommaKeyword_3_0() { return cCommaKeyword_3_0; }
		
		//syntaxes+=Syntax
		public Assignment getSyntaxesAssignment_3_1() { return cSyntaxesAssignment_3_1; }
		
		//Syntax
		public RuleCall getSyntaxesSyntaxParserRuleCall_3_1_0() { return cSyntaxesSyntaxParserRuleCall_3_1_0; }
		
		//'behavior'
		public Keyword getBehaviorKeyword_4() { return cBehaviorKeyword_4; }
		
		//'='
		public Keyword getEqualsSignKeyword_5() { return cEqualsSignKeyword_5; }
		
		//behaviours+=Behavior
		public Assignment getBehavioursAssignment_6() { return cBehavioursAssignment_6; }
		
		//Behavior
		public RuleCall getBehavioursBehaviorParserRuleCall_6_0() { return cBehavioursBehaviorParserRuleCall_6_0; }
		
		//(',' behaviours+=Behavior)*
		public Group getGroup_7() { return cGroup_7; }
		
		//','
		public Keyword getCommaKeyword_7_0() { return cCommaKeyword_7_0; }
		
		//behaviours+=Behavior
		public Assignment getBehavioursAssignment_7_1() { return cBehavioursAssignment_7_1; }
		
		//Behavior
		public RuleCall getBehavioursBehaviorParserRuleCall_7_1_0() { return cBehavioursBehaviorParserRuleCall_7_1_0; }
	}
	public class SyntaxElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "dsl.xtext.Dsl.Syntax");
		private final Assignment cValueAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cValueSTRINGTerminalRuleCall_0 = (RuleCall)cValueAssignment.eContents().get(0);
		
		//Syntax:
		//	value=STRING;
		@Override public ParserRule getRule() { return rule; }
		
		//value=STRING
		public Assignment getValueAssignment() { return cValueAssignment; }
		
		//STRING
		public RuleCall getValueSTRINGTerminalRuleCall_0() { return cValueSTRINGTerminalRuleCall_0; }
	}
	public class BehaviorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "dsl.xtext.Dsl.Behavior");
		private final Assignment cValueAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cValueSTRINGTerminalRuleCall_0 = (RuleCall)cValueAssignment.eContents().get(0);
		
		//Behavior:
		//	value=STRING;
		@Override public ParserRule getRule() { return rule; }
		
		//value=STRING
		public Assignment getValueAssignment() { return cValueAssignment; }
		
		//STRING
		public RuleCall getValueSTRINGTerminalRuleCall_0() { return cValueSTRINGTerminalRuleCall_0; }
	}
	
	
	private final DSLElements pDSL;
	private final SyntaxElements pSyntax;
	private final BehaviorElements pBehavior;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public DslGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pDSL = new DSLElements();
		this.pSyntax = new SyntaxElements();
		this.pBehavior = new BehaviorElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("dsl.xtext.Dsl".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//DSL:
	//	'syntax' '=' syntaxes+=Syntax (',' syntaxes+=Syntax)*
	//	'behavior' '=' behaviours+=Behavior (',' behaviours+=Behavior)*;
	public DSLElements getDSLAccess() {
		return pDSL;
	}
	
	public ParserRule getDSLRule() {
		return getDSLAccess().getRule();
	}
	
	//Syntax:
	//	value=STRING;
	public SyntaxElements getSyntaxAccess() {
		return pSyntax;
	}
	
	public ParserRule getSyntaxRule() {
		return getSyntaxAccess().getRule();
	}
	
	//Behavior:
	//	value=STRING;
	public BehaviorElements getBehaviorAccess() {
		return pBehavior;
	}
	
	public ParserRule getBehaviorRule() {
		return getBehaviorAccess().getRule();
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' |
	//	"'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/ *'->'* /';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}