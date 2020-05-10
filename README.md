### Required step before running
1. Install postgresql
2. edit src/db_config.yaml
3. if using docker for pgsql, make sure db_config.yaml match postgresql/Dockerfile ENV
4. run "gradle bootRun"
5. default login is username: admin password: admin