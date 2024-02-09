Documentation de l'API


1. Récupérer tous les produits

   URL : /api/products
   Méthode HTTP : GET
   Description : Récupère la liste de tous les produits disponibles.
   Réponse : Un tableau JSON contenant tous les produits. Chaque produit est représenté par un objet avec les propriétés suivantes :
   id : Identifiant unique du produit (nombre entier).
   name : Nom du produit (chaîne de caractères).
   price : Prix du produit (nombre décimal).

2. Récupérer les détails d'un produit spécifique

   URL : /api/products/:id
   Méthode HTTP : GET
   Description : Récupère les détails d'un produit spécifique en fonction de son identifiant.
   Paramètres :
   id : L'identifiant unique du produit dont on veut récupérer les détails.
   Réponse : Un objet JSON représentant les détails du produit demandé. Les propriétés sont les mêmes que pour la liste des produits.

3. Ajouter un nouveau produit

   URL : /api/products
   Méthode HTTP : POST
   Description : Ajoute un nouveau produit à la liste des produits.
   Données de la requête : Un objet JSON représentant les détails du nouveau produit à ajouter. Les propriétés requises sont name et price.
   Réponse : L'objet JSON du produit nouvellement ajouté, incluant son identifiant unique généré automatiquement.

4. Mettre à jour les détails d'un produit

   URL : /api/products/:id
   Méthode HTTP : PUT
   Description : Met à jour les détails d'un produit spécifique en fonction de son identifiant.
   Paramètres :
   id : L'identifiant unique du produit à mettre à jour.
   Données de la requête : Un objet JSON représentant les nouveaux détails du produit à mettre à jour. Les propriétés pouvant être mises à jour sont name et price.
   Réponse : L'objet JSON du produit mis à jour.

5. Supprimer un produit

   URL : /api/products/:id
   Méthode HTTP : DELETE
   Description : Supprime un produit spécifique en fonction de son identifiant.
   Paramètres :
   id : L'identifiant unique du produit à supprimer.
   Réponse : Aucune donnée renvoyée après la suppression.

Exemples d'utilisation
Exemple 1: Récupérer tous les produits

 -> GET /api/products

Réponse (json) :

    {
        "id": 1,
        "name": "Produit 1",
        "price": 10.99
        },
        {
        "id": 2,
        "name": "Produit 2",
        "price": 19.99
    }


Exemple 2: Récupérer les détails du produit avec l'identifiant 1

 -> GET /api/products/1

Réponse (json) :

    {
        "id": 1,
        "name": "Produit 1",
        "price": 10.99
    }


Notes: 

Désolé pour le désagrément, mais j'ai rencontré un bug qui m'a bloqué dans la progression de la création de mon CRUD et dans la mise en forme de mon front. Peu importe la méthode utilisée (docker ou non), mon front tournait en boucle sur mon localhost et je ne pouvais plus apporter de modifications ni voir les résultats. J'ai sollicité l'aide d'un ami ingénieur, mais malheureusement, nous n'avons pas réussi à trouver de solution.

Je suis vraiment désolé pour cette situation et j'espère que nous pourrons en discuter plus en détail lors d'un entretien.