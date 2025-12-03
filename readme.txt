Ce que j’ai fait pour répondre aux critères liés au Green Code et limiter du code énergivore :


- découpage en microservices pour ne charger que les services nécessaires
- endpoints ciblés (par patient) pour éviter des requêtes massives.


Pistes de refactoring Green :

- ajouter un cache sur certains appels (par exemple l’évaluation du risque si les notes n’ont pas changé) pour éviter des calculs inutile
- surveiller les logs et réduire le niveau de log en production
