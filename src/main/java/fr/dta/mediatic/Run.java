//package fr.dta.mediatic;
//
//import java.util.Date;
//import java.util.List;
//
//import fr.dta.mediatic.loan.Repository.LoanRepository;
//import fr.dta.mediatic.media.Repository.MediaRepository;
//import fr.dta.mediatic.member.Repository.MemberRepository;
//import fr.dta.mediatic.model.Address;
//import fr.dta.mediatic.model.Gender;
//import fr.dta.mediatic.model.Loan;
//import fr.dta.mediatic.model.Media;
//import fr.dta.mediatic.model.Member;
//import fr.dta.mediatic.model.Role;
//import fr.dta.mediatic.model.Subscription;
//import fr.dta.mediatic.model.TypeMedia;
//import fr.dta.mediatic.model.User;
//import fr.dta.mediatic.subscription.Repository.SubscriptionRepository;
//import fr.dta.mediatic.user.Repository.UserRepository;
//
//public class Run {
//    
//    static MediaRepository mediaRepository = MediaRepository.instance();
//    static LoanRepository loadRepository = LoanRepository.instance();
//    static MemberRepository memberRepository = MemberRepository.instance();
//    static SubscriptionRepository subscriptionRepository = SubscriptionRepository.instance();
//    static UserRepository userRepository = UserRepository.instance();
//    
//    /**
//     * There
//     * @param args
//     */
//    public static void main(String[] args) {
//	fillBD();
//	//mediaOperations();
//	memberOperations();
//	
//    }
//
//    /**
//     * Fill the BD
//     */
//    public static void fillBD() {
//	
//	// Users
//	userRepository.persist(new User("admin", "admin", Role.Manager, "Traa", "Baba", "baba@gmail.com", Gender.Homme));
//	userRepository.persist(new User("biblio", "bi", Role.Employee, "Martin", "Véronique", "vero@gmail.com", Gender.Femme));
//	
//	// Medias
//	Media md1 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Les derniers jeux de Pompeï", "POUGET Anne"));
//	Media md2 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "aaa", "POUGET Anne"));
//	Media md3= mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Yaël Tautavel", "JAUBERTIE Stéphane"));
//	Media md4 = mediaRepository.persist(new Media(null, TypeMedia.CD, "Trois histoires de Blanche-Neige racontées dans le monde", "MOREL Fabienne, BIZOUERNE Gilles, GASTAUT Charlotte"));
//	Media md5 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Trois histoires du Petit Poucet racontées dans le monde", "MOREL Fabienne, BIZOUERNE Gilles, HAREL Émilie"));
//	Media md6 = mediaRepository.persist(new Media(null, TypeMedia.DVD, "Un piège pour Iphigénie", "BRISOU-PELLEN Evelyne, USDIN Elene"));
//	Media md7 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Yeghvala, la belle sorcière", "GENDRIN Catherine, NOVI Nathalie"));
//	Media md8 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Zeus à la conquête de l'Olympe", "MONTARDRE Hélène, USDIN Elene"));
//	Media md9 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "À fleur de silence", "COULIOU Chantal, BURET Nelly"));
//	Media md10 = mediaRepository.persist(new Media(null, TypeMedia.CD, "Après vous, M. de La Fontaine… Contrefables", "GUDULE"));
//	Media md11 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Carnet de Madame D., septième femme de Barbe Bleue", "AUBIN Chantal"));
//	Media md12 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Cent onze haiku", "BASHÔ Matsuo"));
//	Media md13 = mediaRepository.persist(new Media(null, TypeMedia.DVD, "La bouche pleine", "FRIOT Bernard"));
//	Media md14 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "La nuit même pas peur", "GALEA Claudine"));
//	Media md15 = mediaRepository.persist(new Media(null, TypeMedia.CD, "Le fabuleux fablier", "HENRY Jean-Marie, LEJONC Régis "));
//	Media md16 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Les animaux de tout le monde", "ROUBAUD Jacques"));
//	Media md17 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Les fables de La Fontaine", "CHAGALL Marc"));
//	Media md18 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Mon kdi n'est pas un kdo", "BESNIER Michel, GALERON Henri"));
//	Media md19 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Naturellement", "HENRY Jean-Marie, THOMAS Yves"));
//	Media md20 = mediaRepository.persist(new Media(null, TypeMedia.DVD, "Petits poèmes par trois", "BARON Marc, ZAÜ"));
//	Media md21 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Poème du petit Poucet", "NÈVE Sylvie"));
//	Media md22 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Poèmes", "PRÉVERT Jacques"));
//	Media md23 = mediaRepository.persist(new Media(null, TypeMedia.CD, "Poèmes de la lune et de quelques étoiles", "JOUBERT Jean"));
//	Media md24 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Un rêve sans faim", "DAVID François, THIEBAUT Olivier"));
//	Media md25 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "153 jours en hiver", "PETIT Xavier-Laurent"));
//	Media md26 = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "35 kilos d'espoir", "GAVALDA Anna"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "9 héroïnes de l'antiquité", "HELLER-ARFOUILLÈRE Brigitte"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Bashô", "KERISEL Françoise, CLÉMENT Frédéric"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Ça déménage !", "CHARTRE Cécile"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Cascades et gaufres à gogo", "PARR Maria"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Claudine de Lyon", "HELGERSON Marie-Christine"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Demain les fleurs ", "LENAIN Thierry, BROUILLARD Anne"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Harry Potter à l'école des sorciers", "ROWLING Joanne Kathleen"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Hector le bouclier de Troie", "HUGO Hector, USDIN Elene"));
//	mediaRepository.persist(new Media(null, TypeMedia.CD, "Il était une fois le monde", "KACIMI Mohamed, SOLAL Elsa"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Il faut sauver Saïd", "SMADJA Brigitte"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Impossible à dire", "REILLY GIFF Patricia, RIVELAYGUES Laurent"));
//	mediaRepository.persist(new Media(null, TypeMedia.DVD, "Intrigues à Athènes", "THIÈS Paul"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Jonas, le poisson et moi", "WEIL Sylvie"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Kamo l'agence Babel", "PENNAC Daniel, CHABOT Jean-Philippe"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "L.O.L.A.", "MAZARD Claire"));
//	mediaRepository.persist(new Media(null, TypeMedia.CD, "L’agneau qui ne voulait pas être un mouton", "JEAN Didier, ZAD"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "L’arbre sans fin", "PONTI Claude"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "L’enfant océan", "MOURLEVAT Jean-Claude"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "La conspiration des Dieux", "NORMANDON Richard"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "La fiancée du Nil", "JACQ Christian"));
//	mediaRepository.persist(new Media(null, TypeMedia.DVD, "La grande fabrique de mots ", "LESTRADE (de) Agnès,  DOCAMPO Valeria"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "La Lettre déchirée", "BALAERT Ella"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "La loi du Roi Boris", "BARRAQUÉ Gilles, MEURISSE Catherine"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "La rivière à l'envers", "MOURLEVAT Jean-Claude"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "La vie merveilleuse de la princesse Olga", "KA Olivier, LATYK Olivier"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "L'Afrique, petit Chaka", "SELLIER Marie, LESAGE Marion"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Le cercle des patineurs", "BOUYALA Sabine"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Le garçon qui voulait courir vite", "BOTTERO Pierre"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Le plus vieux de la classe ", "COHEN-JANCA Irène"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Le premier roi du monde : l'épopée de Gilgamesh", "CASSABOIS Jacques"));
//	mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Le professeur de musique", "HASSAN Yaël, BLOCH Serge"));
//
//	// Subscriptions
//	Subscription sub1 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub2 = subscriptionRepository.persist(new Subscription(200, new Date()));
//	Subscription sub3 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub4 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub5 = subscriptionRepository.persist(new Subscription(200, new Date()));
//	Subscription sub6 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub7 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub8 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub9 = subscriptionRepository.persist(new Subscription(200, new Date()));
//	Subscription sub10 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub11 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub12 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub13 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub14 = subscriptionRepository.persist(new Subscription(200, new Date()));
//	Subscription sub15 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub16 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub17 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub18 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub19 = subscriptionRepository.persist(new Subscription(200, new Date()));
//	Subscription sub20 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub21 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub22 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub23 = subscriptionRepository.persist(new Subscription(300, new Date()));
//
//
//	// Membres
//	Member mm1 = memberRepository.persist(new Member(null, "IDFG01", new Date(), "Troun", "Bariste", "baba@gmail.com", Gender.Homme, "3 rue Apre", "01000", "AINVILLE", sub1));
//	Member mm2 = memberRepository.persist(new Member(null, "IDFG02", new Date(), "Blanc", "Fanchon", "baba@gmail.com", Gender.Homme, "40 rue des Lacs", "78800", "HOUILLES", sub2));
//	Member mm3 = memberRepository.persist(new Member(null, "IDFG03", new Date(), "Beaujolie", "Guy", "baba@gmail.com", Gender.Homme, "38 boulevard Bryas", "60100", "CREIL", sub3));
//	Member mm4 = memberRepository.persist(new Member(null, "IDFG04", new Date(), "Pépin", "Talbot", "baba@gmail.com", Gender.Homme, "59 rue des Chaligny", "06200", "NICE", sub4));
//	Member mm5 = memberRepository.persist(new Member(null, "IDFG05", new Date(), "LaGarde", "Heloise", "baba@gmail.com", Gender.Femme, "41 rue Léon Dierx", "59160", "LOMME", sub5));
//	Member mm6 = memberRepository.persist(new Member(null, "IDFG06", new Date(), "Morel", "Loyal", "baba@gmail.com", Gender.Homme, "35 rue Lenotre", "35700", "RENNES", sub6));
//	Member mm7 = memberRepository.persist(new Member(null, "IDFG07", new Date(), "Moreau", "Jacques", "baba@gmail.com", Gender.Homme, "60 Faubourg Saint Honoré", "75018", "PARIS", sub7));
//	Member mm8 = memberRepository.persist(new Member(null, "IDFG08", new Date(), "Champagne", "Lotye", "baba@gmail.com", Gender.Homme, "47 rue Gontier-Patin", "80100", "ABBEVILLE", sub8));
//	Member mm9 = memberRepository.persist(new Member(null, "IDFG09", new Date(), "Guernon", "Dexter", "baba@gmail.com", Gender.Homme, "45 Avenue des Pr'es", "78180", "MONTIGNY-LE-BRETONNEUX", sub9));
//	Member mm10 = memberRepository.persist(new Member(null, "IDFG10", new Date(), "Pitre", "Moore", "baba@gmail.com", Gender.Homme, "67 Rue Marie De Médicis", "06400", "CANNES", sub10));
//	Member mm11 = memberRepository.persist(new Member(null, "IDFG11", new Date(), "LaGarde", "Garland", "baba@gmail.com", Gender.Homme, "47 avenue de Provence", "56000", "VANNES", sub11));
//	Member mm12 = memberRepository.persist(new Member(null, "IDFG12", new Date(), "Plante", "Royden", "baba@gmail.com", Gender.Homme, "7 avenue Jules Ferry", "02200", "SOISSONS", sub12));
//	Member mm13 = memberRepository.persist(new Member(null, "IDFG13", new Date(), "Therrien", "Denise", "baba@gmail.com", Gender.Femme, "98 Chemin Des Bateliers", "74000", "ANNECY", sub13));
//	Member mm14 = memberRepository.persist(new Member(null, "IDFG14", new Date(), "Brochu", "Odo", "baba@gmail.com", Gender.Homme, "67 rue Sadi Carnot", "93600", "AULNAY-SOUS-BOIS", sub14));
//	Member mm15 = memberRepository.persist(new Member(null, "IDFG15", new Date(), "Gervais", "Blondelle", "baba@gmail.com", Gender.Femme, "73 quai Saint-Nicolas", "37100", "TOURS", sub15));
//	Member mm16 = memberRepository.persist(new Member(null, "IDFG16", new Date(), "Nadeau", "Blondelle", "baba@gmail.com", Gender.Femme, "13 place de Miremont", "47300", "VILLENEUVE-SUR-LOT", sub16));
//	Member mm17 = memberRepository.persist(new Member(null, "IDFG17", new Date(), "Ricard", "Josette", "baba@gmail.com", Gender.Femme, "22 rue des Lacs", "62110", "HÉNIN-BEAUMONT", sub17));
//	Member mm18 = memberRepository.persist(new Member(null, "IDFG18", new Date(), "Faure", "Campbell", "baba@gmail.com", Gender.Homme, "91 rue Sébastopol", "97230", "SAINTE-MARIE", sub18));
//	Member mm19 = memberRepository.persist(new Member(null, "IDFG19", new Date(), "Étoile", "Xavier", "baba@gmail.com", Gender.Homme, "7 rue du Faubourg National", "94320", "THIAIS", sub19));
//	Member mm20 = memberRepository.persist(new Member(null, "IDFG20", new Date(), "Rochefort", "Eliot", "baba@gmail.com", Gender.Homme, "48 rue de Raymond Poincaré", "11100", "NARBONNE", sub20));
//	Member mm21 = memberRepository.persist(new Member(null, "IDFG21", new Date(), "Clavet", "Evrard", "baba@gmail.com", Gender.Homme, "18 Faubourg Saint Honoré", "75018", "PARIS", sub21));
//	Member mm22 = memberRepository.persist(new Member(null, "IDFG22", new Date(), "Bureau", "Denis", "baba@gmail.com", Gender.Homme, "11 boulevard de la Liberation", "13013", "MARSEILLE", sub22));
//	Member mm23 = memberRepository.persist(new Member(null, "IDFG23", new Date(), "Marquis", "Brice", "baba@gmail.com", Gender.Homme, "49 rue Descartes", "67200", "STRASBOURG", sub23));
//
//	// Emprunt
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md1, mm1));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md1, mm2));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md2, mm3));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md2, mm3));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md3, mm4));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md4, mm5));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md6, mm6));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md8, mm7));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md9, mm8));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md10, mm8));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md11, mm8));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md12, mm10));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md13, mm11));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md15, mm16));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md16, mm17));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md17, mm18));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md18, mm19));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md19, mm19));
//	loadRepository.persist(new Loan(null, new Date(), new Date(), new Date(), md20, mm19));
//
//    }
//
//    /**
//     * Operations on the media
//     */
//    public static void mediaOperations() {
//
//	// New medias
//	System.out.println("--------NEW MEDIAS---------");
//	Media mediaBookVoltaire = new Media(null, TypeMedia.BOOK, "Candide", "Voltaire");
//	mediaRepository.persist(mediaBookVoltaire);
//	Media mediaBookTrain = new Media(null, TypeMedia.BOOK, "Everything you need to know about Nadir", "BTrain");
//	mediaRepository.persist(mediaBookTrain);
//	System.out.println("--------/NEW MEDIAS---------");
//
//	// Update media
//	System.out.println("--------UPDATE MEDIA---------");
//	mediaBookTrain.setAuthor("b-Train");
//	mediaRepository.merge(mediaBookTrain);
//	System.out.println("--------/UPDATE MEDIA---------");
//
//	// Remove media
//	System.out.println("--------REMOVE MEDIA---------");
//	mediaRepository.remove(mediaBookTrain.getId());
//	System.out.println("--------/REMOVE MEDIA---------");
//
//	// Select all the medias
//	List<Media> listM = mediaRepository.selectAllMedias();
//	System.out.println("--------ALL MEDIAS---------");
//	for (Media m : listM) {
//	    System.out.println(">>>>>>>>>>>MEDIA = " + m);
//	    System.out.println("--------/ALL MEDIAS-------");
//	}
//
//	// Select media by id
//	System.out.println("--------MEDIA BY ID---------");
//	Media mediaFind = mediaRepository.find(mediaBookVoltaire.getId());
//	System.out.println(">>>>>>>>>>>>MEDIA = " + mediaFind.getTitle());
//	System.out.println("--------/MEDIA BY ID---------");
//
//	// Select media by title
//	System.out.println("--------MEDIA BY TITLE---------");
//	listM = mediaRepository.findMediaByTitle("Candi");
//	for (Media m : listM) {
//	    System.out.println(">>>>>>>>>>>MEDIA = " + m);
//	    System.out.println("--------/MEDIA BY TITLE--------");
//	}
//
//	// Select media by author
//	System.out.println("--------MEDIA BY AUTHOR---------");
//	listM = mediaRepository.findMediaByAuthor("Volt");
//	for (Media m : listM) {
//	    System.out.println(">>>>>>>>>>>MEDIA = " + m);
//	}
//	System.out.println("--------/MEDIA BY AUTHOR---------");
//
//	// Select media by type
//	System.out.println("--------MEDIA BY TYPE---------");
//	listM = mediaRepository.findMediaByType(TypeMedia.BOOK);
//	for (Media m : listM) {
//	    System.out.println(">>>>>>>>>>>MEDIA = " + m);
//	}
//	System.out.println("--------/MEDIA BY TYPE---------");
//	
//	// TODO selectMediasFromMember
//    }
//    
//    /**
//     * Operations on the media
//     */
//    public static void memberOperations() {
//
//	// New member
//	System.out.println("--------NEW MEMBER---------");
//	Subscription sub1 = subscriptionRepository.persist(new Subscription(300, new Date()));
//	Subscription sub2 = subscriptionRepository.persist(new Subscription(200, new Date()));
//	Member member1 = new Member(null, "IDFG01", new Date(), "Dupont", "Jacques", "jacques@gmail.com", Gender.Homme, "3 rue Apre", "01000", "AINVILLE", sub1);
//	memberRepository.persist(member1);
//	Member member2 = new Member(null, "IDFG45", new Date(), "Dupond", "Jacquart", "jacques2@gmail.com", Gender.Homme, "3 rue Apres", "01000", "AINVILLE", sub2);
//	memberRepository.persist(member2);
//	System.out.println("--------/NEW MEMBER---------");
//
//	// Update member
//	System.out.println("--------UPDATE MEMBER---------");
//	member2.setAddress(new Address("4 rue Delphie", "56000", "DELPHE"));
//	memberRepository.merge(member1);
//	System.out.println("--------/UPDATE MEMBER---------");
//
//	// Remove member
//	System.out.println("--------REMOVE MEMBER---------");
//	memberRepository.remove(member1.getId());
//	System.out.println("--------/REMOVE MEMBER---------");
//
//	List<Member> listM;
//	
//	// Select all the members
//	listM = memberRepository.selectAllMembers();
//	System.out.println("--------ALL MEMBERS---------");
//	for (Member m : listM) {
//	    System.out.println(">>>>>>>>>>>MEMBER = " + m);
//	    System.out.println("--------/ALL MEMBERS-------");
//	}
//
//	// Select member by id
//	System.out.println("--------MEMBER BY ID---------");
//	Member memberFind = memberRepository.find(member2.getId());
//	System.out.println(">>>>>>>>>>>>MEMBER = " + memberFind.getPerson().getFirstname());
//	System.out.println("--------/MEMBER BY ID---------");
//	
//	// Select member by id partiel
//	System.out.println("--------MEMBER BY ID PARTIAL---------");
//	listM = memberRepository.findMembersByIdPartial(member1.getIdentifier());
//	for (Member m : listM) {
//	    System.out.println(">>>>>>>>>>>MEMBER = " + m);
//	    System.out.println("--------/MEMBER BY ID PARTIAL--------");
//	}
//
//	// Select member by name
//	System.out.println("--------MEMBER BY NAME---------");
//	listM = memberRepository.findMemberByName("Dup", "ja");
//	for (Member m : listM) {
//	    System.out.println(">>>>>>>>>>>MEMBER = " + m);
//	    System.out.println("--------/MEMBER BY NAME--------");
//	}
//
//	// Select members from media
//	System.out.println("--------MEMBER FROM MEDIA---------");
//	Media mediaBookTrain = mediaRepository.persist(new Media(null, TypeMedia.BOOK, "Everything you need to know about Nadir", "BTrain"));
//	listM = memberRepository.findMembersFromMedia(mediaBookTrain);
//	for (Member m : listM) {
//	    System.out.println(">>>>>>>>>>>MEMBER = " + m);
//	}
//	System.out.println("--------/MEMBER FROM MEDIA---------");
//
//	//TODO findMemberByIdOrNames
//    }
//
//}
