package fr.dta.mediatic;

import java.util.Date;
import java.util.List;

import fr.dta.mediatic.loan.dao.LoanDAO;
import fr.dta.mediatic.loan.model.Loan;
import fr.dta.mediatic.media.dao.MediaDAO;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.media.model.TypeMedia;
import fr.dta.mediatic.member.dao.MemberDAO;
import fr.dta.mediatic.member.model.Member;
import fr.dta.mediatic.model.Gender;
import fr.dta.mediatic.subscription.dao.SubscriptionDAO;
import fr.dta.mediatic.subscription.model.Subscription;

public class Run {

	public static void main(String[] args) {
		fillBD();
	}
	
	
	/**
	 * Fill the BD
	 */
	public static void fillBD() {
		MediaDAO mediaDAO = MediaDAO.instance();
		LoanDAO loadDAO = LoanDAO.instance();
		MemberDAO memberDAO = MemberDAO.instance();
		SubscriptionDAO subscriptionDAO = SubscriptionDAO.instance();
		
		// Medias
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Les derniers jeux de Pompeï","POUGET Anne"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "aaa","POUGET Anne"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Yaël Tautavel","JAUBERTIE Stéphane"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Trois histoires de Blanche-Neige racontées dans le monde","MOREL Fabienne, BIZOUERNE Gilles, GASTAUT Charlotte"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Trois histoires du Petit Poucet racontées dans le monde","MOREL Fabienne, BIZOUERNE Gilles, HAREL Émilie"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Un piège pour Iphigénie","BRISOU-PELLEN Evelyne, USDIN Elene"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Yeghvala, la belle sorcière","GENDRIN Catherine, NOVI Nathalie"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Zeus à la conquête de l'Olympe","MONTARDRE Hélène, USDIN Elene"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "À fleur de silence","COULIOU Chantal, BURET Nelly"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Après vous, M. de La Fontaine… Contrefables","GUDULE"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Carnet de Madame D., septième femme de Barbe Bleue","AUBIN Chantal"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Cent onze haiku","BASHÔ Matsuo"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "La bouche pleine","FRIOT Bernard"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "La nuit même pas peur","GALEA Claudine"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Le fabuleux fablier","HENRY Jean-Marie, LEJONC Régis "));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Les animaux de tout le monde","ROUBAUD Jacques"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Les fables de La Fontaine","CHAGALL Marc"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Mon kdi n'est pas un kdo","BESNIER Michel, GALERON Henri"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Naturellement","HENRY Jean-Marie, THOMAS Yves"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Petits poèmes par trois","BARON Marc, ZAÜ"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Poème du petit Poucet","NÈVE Sylvie"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Poèmes","PRÉVERT Jacques"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Poèmes de la lune et de quelques étoiles","JOUBERT Jean"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Un rêve sans faim","DAVID François, THIEBAUT Olivier"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "153 jours en hiver","PETIT Xavier-Laurent"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "35 kilos d'espoir","GAVALDA Anna"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "9 héroïnes de l'antiquité","HELLER-ARFOUILLÈRE Brigitte"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Bashô","KERISEL Françoise, CLÉMENT Frédéric"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Ça déménage !","CHARTRE Cécile"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Cascades et gaufres à gogo","PARR Maria"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Claudine de Lyon","HELGERSON Marie-Christine"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Demain les fleurs ","LENAIN Thierry, BROUILLARD Anne"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Harry Potter à l'école des sorciers","ROWLING Joanne Kathleen"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Hector le bouclier de Troie","HUGO Hector, USDIN Elene"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Il était une fois le monde","KACIMI Mohamed, SOLAL Elsa"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Il faut sauver Saïd","SMADJA Brigitte"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Impossible à dire","REILLY GIFF Patricia, RIVELAYGUES Laurent"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Intrigues à Athènes","THIÈS Paul"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Jonas, le poisson et moi","WEIL Sylvie"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Kamo l'agence Babel","PENNAC Daniel, CHABOT Jean-Philippe"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "L.O.L.A.","MAZARD Claire"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "L’agneau qui ne voulait pas être un mouton","JEAN Didier, ZAD"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "L’arbre sans fin","PONTI Claude"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "L’enfant océan","MOURLEVAT Jean-Claude"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "La conspiration des Dieux","NORMANDON Richard"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "La fiancée du Nil","JACQ Christian"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "La grande fabrique de mots ","LESTRADE (de) Agnès,  DOCAMPO Valeria"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "La Lettre déchirée","BALAERT Ella"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "La loi du Roi Boris","BARRAQUÉ Gilles, MEURISSE Catherine"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "La rivière à l'envers","MOURLEVAT Jean-Claude"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "La vie merveilleuse de la princesse Olga","KA Olivier, LATYK Olivier"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "L'Afrique, petit Chaka","SELLIER Marie, LESAGE Marion"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Le cercle des patineurs","BOUYALA Sabine"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Le garçon qui voulait courir vite","BOTTERO Pierre"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Le plus vieux de la classe ","COHEN-JANCA Irène"));
		mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Le premier roi du monde : l'épopée de Gilgamesh","CASSABOIS Jacques"));
		Media md1 = mediaDAO.persist(new Media(null, TypeMedia.BOOK, "Le professeur de musique","HASSAN Yaël, BLOCH Serge"));
		
		// Subscriptions
		Subscription sub1 = subscriptionDAO.persist(new Subscription(300, new Date()));
		
		// Membres
		Member mm1 = memberDAO.persist(new Member(null, new Date(), "TRAIN", "BAPTISTE", "baba@gmail.com", Gender.Homme, "sdf", 0, "MTP", sub1));
		
		// Emprunt
		loadDAO.persist(new Loan(null, new Date(), new Date(), new Date(), md1, mm1));

	}
	
	
	/**
	 * Operations on the media
	 */
	public static void mediaOperations() {
		// Instanciation
		MediaDAO mediaDAO = MediaDAO.instance();
		
		// New medias
		System.out.println("--------NEW MEDIAS---------");
		Media mediaBookVoltaire = new Media(null, TypeMedia.BOOK, "Candide", "Voltaire");
		mediaDAO.persist(mediaBookVoltaire);
		Media mediaBookTrain = new Media(null, TypeMedia.BOOK, "Everything you need to know about Nadir", "BTrain");
		mediaDAO.persist(mediaBookTrain);
		System.out.println("--------/NEW MEDIAS---------");
		
		// Update media
		System.out.println("--------UPDATE MEDIAS---------");
		mediaBookTrain.setAuthor("b-Train");
		mediaDAO.merge(mediaBookTrain);
		System.out.println("--------/UPDATE MEDIAS---------");
		
		// Remove media
		System.out.println("--------REMOVE MEDIAS---------");
		mediaDAO.remove(mediaBookTrain.getId());
		System.out.println("--------/REMOVE MEDIAS---------");

		// Select all the medias
		List<Media> listM = mediaDAO.selectAllMedias();
		System.out.println("--------ALL MEDIAS---------");
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
			System.out.println("--------/ALL MEDIAS-------");
		}
		
		// Select media by id
		System.out.println("--------MEDIA BY ID---------");
		Media mediaFind = mediaDAO.find(mediaBookVoltaire.getId());
		System.out.println(">>>>>>>>>>>>MEDIA = " + mediaFind.getTitle());
		System.out.println("--------/MEDIA BY ID---------");
		
		// Select media by title
		System.out.println("--------MEDIA BY TITLE---------");
		listM = mediaDAO.findMediaByTitle("Candi");
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
			System.out.println("--------/MEDIA BY TITLE--------");
		}
		
		// Select media by title
		System.out.println("--------MEDIA BY AUTHOR---------");
		listM = mediaDAO.findMediaByAuthor("Volt");
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
		}
		System.out.println("--------/MEDIA BY AUTHOR---------");
		
		// Select media by type
		System.out.println("--------MEDIA BY TYPE---------");
		listM = mediaDAO.findMediaByType(TypeMedia.BOOK);
		for (Media m : listM) {
			System.out.println(">>>>>>>>>>>MEDIA = " + m);
		}
		System.out.println("--------/MEDIA BY TYPE---------");
	}
	
	

}
