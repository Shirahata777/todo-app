FROM node:15.2.0-alpine3.10

WORKDIR /todo-front


RUN apk update \
    && apk add --no-cache --virtual .gyp python make g++ nodejs yarn  \
    && apk --no-cache add avahi-dev \
    && npm update \
    && npm install \
    && apk del .gyp

COPY ./todo-front .

RUN yarn add global vue-cli
RUN yarn add global nuxt
RUN yarn add global create-nuxt-app
RUN yarn add global npm-check-updates
RUN yarn

ENV HOST 0.0.0.0
EXPOSE 8080

# CMD ["yarn", "run", "dev"]