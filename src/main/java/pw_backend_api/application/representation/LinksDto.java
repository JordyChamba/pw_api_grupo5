package pw_backend_api.application.representation;

public class LinksDto {
     public String rel;
        public String href;

        public LinksDto(String rel, String href) {
            this.rel = rel;
            this.href = href;
        }

        public LinksDto() {
        }

}
