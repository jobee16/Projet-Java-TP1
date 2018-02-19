package App;

	import metier.Clients;
	import metier.Comptes;
	import metier.Transactions;
	import java.util.Scanner;
	import java.util.Date;
	import java.util.ArrayList;

	public class GestionBanque {
		
		public static void main(String[] args) {
		// TODO Auto-generated method stub
			GestionBanque bankLauncher =  new GestionBanque();
			bankLauncher.affichage();
		}


		public GestionBanque() {
			// TODO Auto-generated constructor stub
		}
		
		Clients clt = new Clients();
		Double taux, montant, solde_apres_t, solde_avant_t, montantTrans;
		Date date = new Date();
		Scanner reader = new Scanner(System.in);
		int numCompte,numTrans;
		String typeTransac;
		
		Comptes cpt = new Comptes();
		char choix ='\0';
		ArrayList<Comptes> lstCpte = new ArrayList<Comptes>();
		ArrayList<Transactions> listeTransaction = new ArrayList<Transactions>();
		Date dateTrans = new Date();
		
		public void creerCompte()
		{
			try
			{
				System.out.println("Numero du compte (Entier positif)\n");
				int cpid = reader.nextInt();
				while(cpid<=0)
				{
					System.err.println("Numero du compte (Entier positif)\n");
					cpid = reader.nextInt();
				}
				
						boolean trouver = false;
					for (Comptes cpte_tenu : lstCpte) 
					{
						int tempCompte = cpte_tenu.getIdCompte();
						if (tempCompte == cpid)
						{
							System.err.println("Ce compte exsite: ");
							trouver = true;
							while(cpid<=0)
							{
								System.err.println("Numero du compte(Un entier positif)");
								cpid = reader.nextInt();
							}
						}
					}

					if (trouver == false)
					{
						
					System.out.println("Numero du client (Un entier positif)\n");
					int clid = reader.nextInt();
					while(clid<=0)
					{
						System.err.println("Numero du client (Un entier positif)\n");
						clid = reader.nextInt();
					}
					System.out.println("Client nom\n");
					String clnom = reader.next();
					System.out.println("Solde compte -( Un nombre positif)\n");
					double cpsolde = reader.nextDouble();
					while(cpsolde<=0)
					{
						System.err.println("Solde compte -(Un nombre positif)\n");
						cpsolde = reader.nextDouble();
					}
					System.out.println("Type du compte (Epargne ou Courant)\n");
					String typeCpte = reader.next();
					System.out.println("Taux du compte -(Un decimal superieur a 0 et inferieur a 1)\n");
					taux = reader.nextDouble();
					while((taux<=0)||(taux>=1))
					{
						System.err.println("Taux du compte -(Un decimal superieur a 0 et inferieur a 1)\n");
						taux = reader.nextDouble();
					}
					Date dc = new Date();
					cpt= new Comptes(cpid,clid,clnom,cpsolde,taux,dc,typeCpte);
					clt = new Clients(cpt.getIdClient(),cpt.getNomClient(),1);
					typeTransac = "Depot";
					montantTrans = cpsolde;
					numTrans = listeTransaction.size()+1;
					lstCpte.add(cpt);
					solde_avant_t = 0.0;
					solde_apres_t = montantTrans;
					Transactions trans = new Transactions(numTrans, cpt.getIdClient(), cpt.getIdCompte(), dateTrans, typeTransac,montantTrans,solde_avant_t,solde_apres_t);
					listeTransaction.add(trans);
					
					System.out.println("\n");
					System.out.println("Compte: ");
							
					for (int x=0; x<lstCpte.size(); x++)
						{
							System.out.println(lstCpte.get(x).toString());
							System.out.println("");
						}
					}
			}
			catch(Exception e)
			{
				System.out.println("Erreur, veillez rentrer des donnees correctes");
			}
		}
		
		public void verifierBalance()
		{
			try
			{
				System.out.println("Entrez le numero du Compte :");
				numCompte = reader.nextInt();
				while(numCompte<=0)
				{
					System.err.println("Numero du compte - NB: Un entier positif");
					numCompte = reader.nextInt();
				}
				if (lstCpte.isEmpty())
				{
					System.err.println("Ce numero de compte n'existe pas.");
				}
				
				else
				{
					boolean trouver = false;
					for (Comptes cpte_tenu : lstCpte) 
					{
						int tempCompte = cpte_tenu.getIdCompte();
						if (tempCompte == numCompte)
						{
							cpte_tenu.verifierSolde(cpte_tenu.getIdCompte());
							trouver = true;
						}

					}

					if (trouver == false)
					{
						System.err.println("Ce compte n'existe pas.");

					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		public void Depot()
		{
			try
			{
				System.out.println("Entrez le numero du Compte :");
				numCompte = reader.nextInt();
				while(numCompte<=0)
				{
					System.err.println("Numero du compte(Un entier positif)");
					numCompte = reader.nextInt();
				}
					if (lstCpte.isEmpty())
					{
						System.err.println("La banque n'a pas de compte enregistré encore.");
					}
				
					else
					{
						boolean trouver = false;
						for (Comptes cpte_tenu : lstCpte) 
						{
							int tempCompte = cpte_tenu.getIdCompte();
							if (tempCompte == numCompte)
							{
								System.out.println("Entrez le montant(Un montant positif):\n");
								montant = reader.nextDouble();
								while(montant<=0)
								{
									System.err.println("Entrez le montant(Un montant positif):\n");
									montant = reader.nextDouble();
								}
								solde_avant_t = cpte_tenu.getSoldeCompte();
								solde_apres_t = solde_avant_t + montant;
								typeTransac = "Depot";
								montantTrans = montant;
								numTrans = listeTransaction.size()+1;
								Transactions trans = new Transactions(numTrans, cpte_tenu.getIdCompte(),cpte_tenu.getIdClient(),dateTrans,typeTransac,montantTrans,solde_avant_t,solde_apres_t);
								listeTransaction.add(trans);
								System.out.println("Vous aviez "+solde_avant_t+" sur votre compte, \n"
										+ "vous venez de faire un depot de: "+montant+", La: "+solde_apres_t+" sur votre compte");
								System.out.println("\n");
								cpte_tenu.depot(montant);
								trouver = true;
							}
						}

					if (trouver == false)
					{
						System.err.println("Ce numero de compte n'existe pas.");

					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		public void Retrait()
		{
			try
			{
				System.out.println("Entrez le numero du Compte :");
				numCompte = reader.nextInt();
				while(numCompte<=0)
				{
					System.err.println("Numero du compte(Un entier positif)");
					numCompte = reader.nextInt();
				}
				if (lstCpte.isEmpty())
				{
					System.err.println("La banque n'a pas encore de compte enregisté.");
				}
				
				else
				{
					boolean trouver = false;
					for (Comptes cpte_tenu : lstCpte) 
					{
						int tempCompte = cpte_tenu.getIdCompte();
						if (tempCompte == numCompte)
						{
							System.out.println("Entrez le montant a enlever - NB: un montant positif inferieur a votre solde");
							montant = reader.nextDouble();
							while((montant<=0)||(montant>=cpte_tenu.getSoldeCompte()))
							{
								System.err.println("Entrez le montant a enlever - NB: un montant positif inferieur a votre solde");
								montant = reader.nextDouble();
							}
							solde_avant_t = cpte_tenu.getSoldeCompte();
							solde_apres_t = solde_avant_t - montant;
							typeTransac = "Retrait";
							montantTrans = montant;
							numTrans = listeTransaction.size()+1;
							Transactions trans = new Transactions(numTrans, cpte_tenu.getIdCompte(),cpte_tenu.getIdClient(),dateTrans,typeTransac,montantTrans,solde_avant_t,solde_apres_t);
							listeTransaction.add(trans);
							System.out.println("Vous aviez "+solde_avant_t+" sur votre compte, \n"
									+ "vous avez fait un retrait de: "+montant+", la balance actuelle est : "+solde_apres_t+" sur votre compte");
							System.out.println("\n");
							cpte_tenu.retrait(montant);
							
							trouver = true;
						}

					}

					if (trouver == false)
					{
						System.err.println("Ce numero de compte n'existe pas.");

					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}	
		}
		
		public void verifyQteCompte()
		{
			try
			{
				System.out.println("Entrez le numero du client(Un entier positif):");
				int idClient = reader.nextInt();
				while (idClient <= 0)
				{
					System.err.println("le numero du client(Un entier positif):");
					idClient = reader.nextInt();
				}
				if (lstCpte.isEmpty())
				{
					System.err.println("La banque n'a pas de compte pour ce client.");
				}

				else
				{
					boolean trouver = false;
					int i = 0;
					for (Comptes cpte_tenu : lstCpte)
					{
						int tempCompte = cpte_tenu.getIdClient();
						if (tempCompte == idClient) 
						{
							++i;
							trouver = true;
						}
					}
					System.err.println("Nombre de Compte: "+i);
		
					if (trouver == false)
					{
						System.err.println("Ce client n'existe pas.");

					}
				}
			}
			
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		public void rapportTransaction()
		{
			try
			{
				for (int x=0; x<listeTransaction.size(); x++)
				{
					System.out.println("");
					System.out.println(listeTransaction.get(x).toString());
					System.out.println("");
				}
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		public void calculInteret()
		{
			try
			{
				for (int x=0; x<lstCpte.size(); x++)
				{
					lstCpte.get(x).setSoldeCompte(lstCpte.get(x).getSoldeCompte()*(1 + lstCpte.get(x).getTaux()));
					System.out.println("");
					System.out.println(lstCpte.get(x).toString());
					System.out.println("");
				}
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		public void consulterCompte()
		{
			try
			{
				for (int x=0; x<lstCpte.size(); x++)
				{
					System.out.println("\t\t");
					System.out.println(lstCpte.get(x).toString());
					System.out.println("\t\t");
				}
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}

		
		public void messageDefault()
		{
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("     Vous avez fait un mauvais choix, choisissez une option valide   ");
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("\n");
		}
		
		
		public void affichage()
		{
			do
			{
				System.out.println("\n");
				System.out.println("\t  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("\t<\t---------------------------------------- BG BANQUE -------------------------------------------     >");
				System.out.println("\t  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<               >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println("\t\tWELCOME TO GB BANK             \t\t\t\t              Bienvenue à la BG Banque ");
				System.out.println("\n");
				System.out.println("\tLISTE D'OPTION");
				System.out.println("\t\tA - Creer un compte");
				System.out.println("\t\tB - Verifier solde compte");
				System.out.println("\t\tC - Faire un depot ");
				System.out.println("\t\tD - Faire un retrait");
				System.out.println("\t\tE - Verifier le nombre de compte qu'a un client");
				System.out.println("\t\tG - Calculer l'interet des comptes");
				System.out.println("\t\tH - Consulter tous les comptes");
				System.out.println("\t\tF - Rapport de transaction des comptes");
				System.out.println("\t\tO - EXIT");
				System.out.println("\n");
				System.out.println("\t\t------------------------------------------------------------------------------------------------------------");
				System.out.println("\t\tFaites un choix");
				System.out.println("\t\t------------------------------------------------------------------------------------------------------------");
				
				choix = reader.next().charAt(0);
				
				switch(choix)
				{
					case 'a':
					case 'A':
							creerCompte();
						break;
				
					case 'b':
					case 'B':
							verifierBalance();
						break;
					
					case 'c':
					case 'C':
							Depot();
						break;
					
					case 'd':
					case 'D':
							Retrait();
						break;
						
						
					case 'e':
					case 'E':
						verifyQteCompte();
					break;
							
					case 'g':
					case 'G':
							calculInteret();
						break;
						
					case 'h':
					case 'H':
							consulterCompte();
						break;
						
					case 'o':
					case 'O':
						 //messageAurevoir();
						System.out.println("\t\t------------------------------------------------------------------------------------------------------------");
						System.out.println("\t\t     Merci de votre fidelité a la BG banque, à bientot");
						System.out.println("\t\t------------------------------------------------------------------------------------------------------------");
						System.exit(0);
						break;
						
					case 'f':
					case 'F':
							rapportTransaction();
							break;
						
					default:
						messageDefault();
						break;
				}
			}
			
			while(choix != 'O');
				
			reader.close();
		}
		
	}


	


