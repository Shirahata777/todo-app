<template>
  <div>
    <section class="section">
      <div class="section__inner">
        <div class="section__button-wrapper">
          <button
            type="button"
            data-micromodal-trigger="modal-1"
            class="button material-icons"
            v-show="modalButtonShow"
          >
            <v-icon size="70">mdi-calendar-edit</v-icon>
          </button>
        </div>
      </div>
    </section>

    <div class="modal micromodal-slide" id="modal-1" aria-hidden="true">
      <div class="modal__overlay" tabindex="-1" data-micromodal-close>
        <div
          class="modal__container"
          role="dialog"
          aria-modal="true"
          aria-labelledby="modal-1-title"
        >
          <header class="modal__header">
            <button
              type="button"
              class="modal__close"
              aria-label="Close modal"
              data-micromodal-close
            ></button>
          </header>
          <main class="modal__content" id="modal-1-content">
            <EventForm @addEventData="emitAddEventData"/>
          </main>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import EventForm from "~/components/event-form.vue";
export default {
  mounted() {
    this.init();
  },
  data: () => ({
    addEventData: {
      title: "",
      start: "",
      end: "",
      contents: "",
    },
    modalButtonShow: true
    
  }),
  component: {
    EventForm,
  },
  methods: {
    init() {
      MicroModal.init({
        // ここにオプションを書いていく
        disableScroll: true,
        awaitOpenAnimation: true,
        awaitCloseAnimation: true,
        onShow: (modal) => {
          console.info(`${modal.id} is shown`);
          this.modalButtonShow = false;
        }, // [1]
        onClose: (modal) => {
          console.info(`${modal.id} is hidden`);
          this.modalButtonShow = true;
        }, // [2]
        // openTrigger: 'data-custom-open', // [3]
        // closeTrigger: 'data-custom-close', // [4]
        // openClass: 'is-open', // [5]
        disableFocus: false, // [7]
        debugMode: true, // [10]
      });
    },
    emitAddEventData(addEventData) {
      this.addEventData = addEventData;
      this.sentAddEventData()
    },
    sentAddEventData() {
      console.log(this.addEventData);
      this.$emit("addEventData", this.addEventData);
    },
  },
};
</script>
<style lang="scss" scoped>
section.section {
  position: fixed;
  top: 80%;
  left: 90%;
  z-index: 5;
}
.button {
  width: 100%;
  height: 100%;
  // padding: 10px 16px;
  color: #000000;
  font-weight: 700;
  font-size: 55px;
  transition: 0.3s cubic-bezier(0.55, 0.085, 0.68, 0.53);
}

.button:hover,
.button:focus {
  background: transparent;
  color: #000;
}

/* Modal */
.modal {
  font-family: -apple-system, BlinkMacSystemFont, avenir next, avenir,
    helvetica neue, helvetica, ubuntu, roboto, noto, segoe ui, arial, sans-serif;
}

.modal__overlay {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 20%;
  left: 40%;
  z-index: 5;
  // background: rgba(0, 0, 0, 0.6);
}

.modal__container {
  // max-height: 100vh;
  // padding: 30px;
  background-color: #fff;
  overflow-y: auto;
  border-radius: 20px;
  padding: 10px;
}

.modal__header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.modal__title {
  font-weight: 600;
  font-size: 1.25rem;
  line-height: 1.25;
}

.modal__close::before {
  content: "\2715";
}

.modal__content {
  margin-top: 2rem;
  margin-bottom: 2rem;
  line-height: 1.5;
}

/* Animation */
@keyframes mmfadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

@keyframes mmfadeOut {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}

@keyframes mmslideIn {
  from {
    transform: translateY(10%);
  }
  to {
    transform: translateY(0);
  }
}

@keyframes mmslideOut {
  from {
    transform: translateY(0);
  }
  to {
    transform: translateY(-10%);
  }
}

.micromodal-slide {
  display: none;
}

.micromodal-slide.is-open {
  display: block;
}

.micromodal-slide[aria-hidden="false"] .modal__overlay {
  animation: mmfadeIn 0.8s cubic-bezier(0.34, 0.07, 0.095, 0.995);
}

.micromodal-slide[aria-hidden="false"] .modal__container {
  animation: mmslideIn 0.8s cubic-bezier(0.34, 0.07, 0.095, 0.995);
}

.micromodal-slide[aria-hidden="true"] .modal__overlay {
  animation: mmfadeOut 0.8s cubic-bezier(0.34, 0.07, 0.095, 0.995);
}

.micromodal-slide[aria-hidden="true"] .modal__container {
  animation: mmslideOut 0.8s cubic-bezier(0.34, 0.07, 0.095, 0.995);
}

.micromodal-slide .modal__container,
.micromodal-slide .modal__overlay {
  will-change: transform;
}

@media screen and (max-width: 640px) {
  section.section {
    left: 80%;
  }
  .modal__overlay {
    top: 10%;
    left: 40%;
  }
}
</style>
