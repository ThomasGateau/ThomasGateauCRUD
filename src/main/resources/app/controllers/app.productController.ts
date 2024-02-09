import { IController } from 'angular';

export class ProductController implements IController {
    public product: any;
    public id: string;

    static $inject = ['$http', '$location'];
    constructor(private $http: ng.IHttpService, private $location: ng.ILocationService) {
            this.getProductDetails()
    }

    getProductDetails(): void {
        const params = new URLSearchParams(window.location.search)
        this.id = params.get('id')
        this.$http.get('http://localhost:8080/api/products/' + this.id)
            .then(response => {
                this.product = response.data;
            })
            .catch(error => {
                console.error('Error fetching product details', error);
            });
    }

    updateProductDetails(product: any): void {
        this.$http.put('http://localhost:8080/api/products/' + product.id, product)
            .then(response => {
                // Redirect to the homepage
                window.location.href = 'http://localhost:8080'
            })
            .catch(error => {
                console.error('Error updating product details', error);
            });
    }
}